package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataOutputStream;
import java.io.IOException;

public class ShortWriter implements PacketDataSerializerWriter {

    private short shortToEncode;

    public ShortWriter(short shortToEncode) {
        this.shortToEncode = shortToEncode;
    }

    @Override
    public void write(DataOutputStream buffer) throws IOException {
        buffer.writeShort(shortToEncode);
    }

    public void setShortToEncode(short shortToEncode) {
        this.shortToEncode = shortToEncode;
    }
}
