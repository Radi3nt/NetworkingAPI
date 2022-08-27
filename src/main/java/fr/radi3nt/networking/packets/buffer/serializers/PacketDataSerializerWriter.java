package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataOutputStream;
import java.io.IOException;

public interface PacketDataSerializerWriter {

    void write(DataOutputStream buffer) throws IOException;

}
