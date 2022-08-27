package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IntWriter implements PacketDataSerializerWriter {

    private int intToEncode;

    public IntWriter(int intToEncode) {
        this.intToEncode = intToEncode;
    }

    @Override
    public void write(DataOutputStream buffer) throws IOException {
        buffer.writeInt(intToEncode);
    }

    public void setIntToEncode(int intToEncode) {
        this.intToEncode = intToEncode;
    }
}
