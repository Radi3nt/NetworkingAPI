package fr.radi3nt.networking.packets.buffer.serializers;

import java.nio.ByteBuffer;

public class StringReader implements PacketDataSerializerReader {

    private String stringResult;
    private final ShortReader shortReader = new ShortReader();
    private final CharReader charReader = new CharReader();

    @Override
    public void read(ByteBuffer buffer) {
        shortReader.read(buffer);
        short stringLength = shortReader.getShortResult();

        char[] chars = new char[stringLength];
        for (int i = 0; i < stringLength; i++) {
            charReader.read(buffer);
            chars[i]=charReader.getCharResult();
        }

        stringResult = new String(chars);
    }

    public String getStringResult() {
        return stringResult;
    }
}
