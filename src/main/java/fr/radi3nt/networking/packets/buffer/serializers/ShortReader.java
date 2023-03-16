package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataInputStream;
import java.io.IOException;

public class ShortReader implements PacketDataSerializerReader {

    private short shortResult;

    @Override
    public void read(DataInputStream buffer) throws IOException {
        shortResult = buffer.readShort();
    }

    public short getShortResult() {
        return shortResult;
    }
}
