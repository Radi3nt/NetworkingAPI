package fr.radi3nt.networking.packets.buffer.serializers;

import java.nio.ByteBuffer;

public class ByteReader implements PacketDataSerializerReader {

    private byte charResult;

    @Override
    public void read(ByteBuffer buffer) {
        charResult = buffer.get();
    }

    public byte getByteResult() {
        return charResult;
    }
}
