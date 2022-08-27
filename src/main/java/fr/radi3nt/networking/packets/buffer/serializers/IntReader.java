package fr.radi3nt.networking.packets.buffer.serializers;

import java.nio.ByteBuffer;

public class IntReader implements PacketDataSerializerReader {

    private int intResult;

    @Override
    public void read(ByteBuffer buffer) {
        intResult = buffer.getInt();
    }

    public int getIntResult() {
        return intResult;
    }
}
