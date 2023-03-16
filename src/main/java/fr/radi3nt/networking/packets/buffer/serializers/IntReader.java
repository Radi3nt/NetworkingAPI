package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataInputStream;
import java.io.IOException;

public class IntReader implements PacketDataSerializerReader {

    private int intResult;

    @Override
    public void read(DataInputStream buffer) throws IOException {
        intResult = buffer.readInt();
    }

    public int getIntResult() {
        return intResult;
    }
}
