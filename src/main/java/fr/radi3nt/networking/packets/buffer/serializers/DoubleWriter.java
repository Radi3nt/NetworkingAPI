package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataOutputStream;
import java.io.IOException;

public class DoubleWriter implements PacketDataSerializerWriter {

    private double doubleToEncode;

    public DoubleWriter(double doubleToEncode) {
        this.doubleToEncode = doubleToEncode;
    }

    @Override
    public void write(DataOutputStream buffer) throws IOException {
        buffer.writeDouble(doubleToEncode);
    }

    public void setDoubleToEncode(double doubleToEncode) {
        this.doubleToEncode = doubleToEncode;
    }
}
