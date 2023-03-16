package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataInputStream;
import java.io.IOException;

public class RawByteReader implements PacketDataSerializerReader {

    private int byteResult;

    @Override
    public void read(DataInputStream buffer) throws IOException {
        byteResult = buffer.read();
    }

    public int getByteResult() {
        return byteResult;
    }
}
