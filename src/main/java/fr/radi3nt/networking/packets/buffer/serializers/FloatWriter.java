package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataOutputStream;
import java.io.IOException;

public class FloatWriter implements PacketDataSerializerWriter {

    private float floatToEncode;

    public FloatWriter(float floatToEncode) {
        this.floatToEncode = floatToEncode;
    }

    @Override
    public void write(DataOutputStream buffer) throws IOException {
        buffer.writeFloat(floatToEncode);
    }

    public void setFloatToEncode(float floatToEncode) {
        this.floatToEncode = floatToEncode;
    }
}
