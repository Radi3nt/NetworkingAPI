package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataInputStream;
import java.io.IOException;

public class FloatReader implements PacketDataSerializerReader {

    private float floatResult;

    @Override
    public void read(DataInputStream buffer) throws IOException {
        floatResult = buffer.readFloat();
    }

    public float getFloatResult() {
        return floatResult;
    }
}
