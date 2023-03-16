package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataInputStream;
import java.io.IOException;

public class CharReader implements PacketDataSerializerReader {

    private char charResult;

    @Override
    public void read(DataInputStream buffer) throws IOException {
        charResult = buffer.readChar();
    }

    public char getCharResult() {
        return charResult;
    }
}
