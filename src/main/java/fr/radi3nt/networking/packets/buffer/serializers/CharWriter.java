package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataOutputStream;
import java.io.IOException;

public class CharWriter implements PacketDataSerializerWriter {

    private char charToEncode;

    public CharWriter(char charToEncode) {
        this.charToEncode = charToEncode;
    }

    @Override
    public void write(DataOutputStream buffer) throws IOException {
        buffer.writeChar(charToEncode);
    }

    public void setCharToEncode(char charToEncode) {
        this.charToEncode = charToEncode;
    }
}
