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
    private final InputStream socketInputStream;
    private ReceivedPacketInfo receivedPacketInfo;

    private final PipedOutputStream bufferOutStream;
    private final InputStream bufferInStream;
    private final DataInputStream inStream;

    public NonBlockingNetworkReader(InputStream socketInputStream) throws IOException {
        this.socketInputStream = socketInputStream;
        this.bufferOutStream = new PipedOutputStream();
        this.bufferInStream = new PipedInputStream(bufferOutStream);

        inStream = new DataInputStream(bufferInStream);
    }

    @Override
    public void read() throws NetworkException {
        try {
            if (receivedPacketInfo!=null) {
                readActualPacket();
            } else {
                readSmallByteAmount();

                int available = bufferInStream.available();
                if (available>=Integer.BYTES) {
                    receivePacket();
                }
            }
        } catch (IOException e) {
            throw new ConnectionClosedException();
        }
    }

    private void receivePacket() throws IOException {
        int packetSize = inStream.readInt();

        receivedPacketInfo = new ReceivedPacketInfo(packetSize);
        readStream(inStream);
        readActualPacket();
    }

    private void readSmallByteAmount() throws NetworkException, IOException {
        int available = socketInputStream.available();
        byte[] bytes = new byte[Math.min(Math.max(1, available), Integer.BYTES-inStream.available())];
        if (bytes.length==0)
            return;

        int readBytes = socketInputStream.read(bytes);
        if (readBytes==-1) {
            throw new ConnectionClosedException();
        } else {
            bufferOutStream.write(bytes, 0, readBytes);
        }
    }

    private void readActualPacket() throws IOException {
        if (receivedPacketInfo.getCurrentRead().unfinished()) {
            receivedPacketInfo.getCurrentRead().blockingRead(socketInputStream);
            finishPacket();
        } else {
            finishPacket();
        }
    }

    private void finishPacket() {
        byte[] bytes = receivedPacketInfo.getCurrentRead().bytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.limit(bytes.length);

        packetBuffers.add(new PacketDataByteBuffer(buffer));
        receivedPacketInfo = null;
    }

    private void readStream(InputStream stream) throws IOException {
        if (receivedPacketInfo.getCurrentRead().unfinished())
            receivedPacketInfo.getCurrentRead().nonBlockingRead(stream);
    }

    @Override
    public ReadablePacketBuffer poll() {
        return packetBuffers.poll();
    }
}
