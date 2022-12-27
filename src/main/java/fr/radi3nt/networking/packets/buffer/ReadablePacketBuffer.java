package fr.radi3nt.networking.packets.buffer;

import fr.radi3nt.networking.packets.buffer.serializers.PacketDataSerializerReader;

public interface ReadablePacketBuffer extends PacketBuffer {

    byte[] array();
    void read(PacketDataSerializerReader serializerReader);

}
