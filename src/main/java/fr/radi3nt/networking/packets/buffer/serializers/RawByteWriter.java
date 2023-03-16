package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataOutputStream;
import java.io.IOException;

public class RawByteWriter implements PacketDataSerializerWriter {

    private int byteToEncode;

    public RawByteWriter(int byteToEncode) {
        this.byteToEncode = byteToEncode;
    }

    @Override
    public void write(DataOutputStream buffer) throws IOException {
        buffer.write(byteToEncode);
    }

    public void setByteToEncode(int byteToEncode) {
        this.byteToEncode = byteToEncode;
    }
}
