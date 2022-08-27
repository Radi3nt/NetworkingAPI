package fr.radi3nt.networking.packets.buffer.serializers;

import java.nio.ByteBuffer;

public interface PacketDataSerializerReader {

    void read(ByteBuffer buffer);

}
