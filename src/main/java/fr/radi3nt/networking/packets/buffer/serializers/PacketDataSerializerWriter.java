package fr.radi3nt.networking.packets.buffer.serializers;

import java.io.DataOutputStream;

public interface PacketDataSerializerWriter {

    void write(DataOutputStream buffer) throws Exception;

}
