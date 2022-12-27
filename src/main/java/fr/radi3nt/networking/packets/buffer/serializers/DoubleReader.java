package fr.radi3nt.networking.packets.buffer.serializers;

import java.nio.ByteBuffer;

public class DoubleReader implements PacketDataSerializerReader {

    private double doubleToRead;

    @Override
    public void read(ByteBuffer buffer) {
        doubleToRead = buffer.getDouble();
    }

    public double getDoubleToRead() {
        return doubleToRead;
    }
}
