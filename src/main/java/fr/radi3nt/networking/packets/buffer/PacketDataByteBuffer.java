package fr.radi3nt.networking.packets.buffer;

import fr.radi3nt.networking.packets.buffer.serializers.PacketDataSerializerReader;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PacketDataByteBuffer implements ReadablePacketBuffer {

    private final DataInputStream dataStream;
    private final int size;

    public PacketDataByteBuffer(DataInputStream buffer, int size) {
        this.dataStream = buffer;
        this.size = size;
    }

    public int size() {
        return size;
    }

    @Override
    public void writeTo(OutputStream outputStream) throws IOException {
        copy(dataStream, outputStream);
    }

    @Override
    public void read(PacketDataSerializerReader serializerReader) throws IOException {
        serializerReader.read(dataStream);
    }

    private void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[8192];
        int length;
        while ((length = in.read(buf)) > 0) {
            out.write(buf, 0, length);
        }
    }
}
