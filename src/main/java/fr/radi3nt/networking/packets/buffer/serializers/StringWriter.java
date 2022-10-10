package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataOutputStream;
import java.io.IOException;

public class StringWriter implements PacketDataSerializerWriter {

    private String stringToEncode;
    private final ShortWriter shortWriter = new ShortWriter((short) -1);
    private final CharWriter charWriter = new CharWriter((char) -1);

    public StringWriter(String stringToEncode) {
        this.stringToEncode = stringToEncode;
    }

    @Override
    public void write(DataOutputStream buffer) throws IOException {
        int length = stringToEncode.length();
        if (length>Short.MAX_VALUE)
            throw new IllegalStateException("String is too big to be sent by packets");
        shortWriter.setShortToEncode((short) length);
        shortWriter.write(buffer);

        char[] chars = stringToEncode.toCharArray();
        for (char aChar : chars) {
            charWriter.setCharToEncode(aChar);
            charWriter.write(buffer);
        }
    }

    public void setStringToEncode(String stringToEncode) {
        this.stringToEncode = stringToEncode;
    }
}
