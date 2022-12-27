package fr.radi3nt.networking.packets.buffer;

import fr.radi3nt.networking.packets.buffer.serializers.PacketDataSerializerReader;

import java.nio.ByteBuffer;

public class PacketDataByteBuffer implements ReadablePacketBuffer {

    private final ByteBuffer buffer;

    public PacketDataByteBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public int size() {
        return buffer.limit();
    }

    @Override
    public byte[] array() {
        return buffer.array();
    }

    @Override
    public void read(PacketDataSerializerReader serializerReader) {
        serializerReader.read(buffer);
    }
}
