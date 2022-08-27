package fr.radi3nt.networking.network.reader;

import fr.radi3nt.networking.exceptions.ConnectionClosedException;
import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.packets.buffer.PacketDataByteBuffer;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NonBlockingNetworkReader implements NetworkReader {

    private final Queue<ReadablePacketBuffer> packetBuffers = new ConcurrentLinkedQueue<>();
    private final InputStream originalInputStream;
    private ReceivedPacketInfo receivedPacketInfo;

    private final PipedInputStream pipedInputStream;
    private final PipedOutputStream pipedOutputStream;
    private final DataInputStream inputStream;

    public NonBlockingNetworkReader(InputStream inputStream) throws IOException {
        this.originalInputStream = inputStream;
        this.pipedInputStream = new PipedInputStream();
        this.pipedOutputStream = new PipedOutputStream(pipedInputStream);
        this.inputStream = new DataInputStream(pipedInputStream);
    }

    @Override
    public void read() throws NetworkException {
        try {
            if (receivedPacketInfo!=null) {
                readIfComplete(true);
            } else {
                int available = inputStream.available();
                if (available>=Integer.BYTES) {
                    receivePacket();
                } else {
                    readOriginal();
                    available = inputStream.available();
                    if (available>=Integer.BYTES) {
                        receivePacket();
                    }
                }
            }
        } catch (IOException e) {
            throw new NetworkException(e);
        }
    }

    private void receivePacket() throws IOException, NetworkException {
        int packetSize = inputStream.readInt();
        receivedPacketInfo = new ReceivedPacketInfo(packetSize);
        readIfComplete(true);
    }

    private void readOriginal() throws NetworkException, IOException {
        int available = originalInputStream.available();
        byte[] bytes = new byte[Math.max(1, available)];
        int readBytes = originalInputStream.read(bytes);
        if (readBytes==-1) {
            throw new ConnectionClosedException();
        } else {
            pipedOutputStream.write(bytes, 0, readBytes);
        }
    }

    private void readIfComplete(boolean readOriginal) throws IOException, NetworkException {
        int available = inputStream.available();
        int packetSize = receivedPacketInfo.getSize();
        if (available >=packetSize) {
            int readBytes = 0;
            byte[] bytes = new byte[packetSize];

            while (readBytes<packetSize) {
                readBytes += inputStream.read(bytes, readBytes, packetSize - readBytes);
            }

            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            buffer.limit(packetSize);

            packetBuffers.add(new PacketDataByteBuffer(buffer));
            receivedPacketInfo = null;
        } else {
            if (readOriginal) {
                readOriginal();
                readIfComplete(false);
            }
        }
    }

    @Override
    public ReadablePacketBuffer poll() {
        return packetBuffers.poll();
    }
}
