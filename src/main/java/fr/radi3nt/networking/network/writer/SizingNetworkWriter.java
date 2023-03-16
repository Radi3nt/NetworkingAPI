package fr.radi3nt.networking.network.writer;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SizingNetworkWriter implements NetworkWriter {

    private final DataOutputStream dataOutputStream;

    public SizingNetworkWriter(OutputStream original) {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(original);
        this.dataOutputStream = new DataOutputStream(bufferedOutputStream);
    }

    @Override
    public void write(ReadablePacketBuffer buffer) throws NetworkException {
        try {
            synchronized (dataOutputStream) {
                dataOutputStream.writeInt(buffer.size());
                buffer.writeTo(dataOutputStream);
                dataOutputStream.flush();
            }
        } catch (IOException e) {
            throw new NetworkException(e);
        }
    }

}
