package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataInputStream;
import java.io.IOException;

public class DoubleReader implements PacketDataSerializerReader {

    private double doubleToRead;

    @Override
    public void read(DataInputStream buffer) throws IOException {
        doubleToRead = buffer.readDouble();
    }

    public double getDoubleToRead() {
        return doubleToRead;
    }
}
