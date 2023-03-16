package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataInputStream;
import java.io.IOException;

public class ByteReader implements PacketDataSerializerReader {

    private byte charResult;

    @Override
    public void read(DataInputStream buffer) throws IOException {
        charResult = buffer.readByte();
    }

    public byte getByteResult() {
        return charResult;
    }
}
