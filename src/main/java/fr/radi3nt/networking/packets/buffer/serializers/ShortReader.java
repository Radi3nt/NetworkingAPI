package fr.radi3nt.networking.packets.buffer.serializers;

import java.nio.ByteBuffer;

public class ShortReader implements PacketDataSerializerReader {

    private short shortResult;

    @Override
    public void read(ByteBuffer buffer) {
        shortResult = buffer.getShort();
    }

    public short getShortResult() {
        return shortResult;
    }
}
