package fr.radi3nt.networking.packets.buffer.serializers;

import java.nio.ByteBuffer;

public class CharReader implements PacketDataSerializerReader {

    private char charResult;

    @Override
    public void read(ByteBuffer buffer) {
        charResult = buffer.getChar();
    }

    public char getCharResult() {
        return charResult;
    }
}
