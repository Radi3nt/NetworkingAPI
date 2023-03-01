package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataOutputStream;
import java.io.IOException;

public class ByteWriter implements PacketDataSerializerWriter {

    private byte byteToEncode;

    public ByteWriter(byte byteToEncode) {
        this.byteToEncode = byteToEncode;
    }

    @Override
    public void write(DataOutputStream buffer) throws IOException {
        buffer.writeByte(byteToEncode);
    }

    public void setByteToEncode(byte charToEncode) {
        this.byteToEncode = charToEncode;
    }
}
