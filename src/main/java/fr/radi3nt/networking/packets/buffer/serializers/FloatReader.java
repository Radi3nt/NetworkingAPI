package fr.radi3nt.networking.packets.buffer.serializers;

import java.nio.ByteBuffer;

public class FloatReader implements PacketDataSerializerReader {

    private float floatResult;

    @Override
    public void read(ByteBuffer buffer) {
        floatResult = buffer.getFloat();
    }

    public float getFloatResult() {
        return floatResult;
    }
}
