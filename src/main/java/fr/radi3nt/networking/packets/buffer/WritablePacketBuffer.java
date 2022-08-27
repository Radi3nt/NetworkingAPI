package fr.radi3nt.networking.packets.buffer;

import fr.radi3nt.networking.exceptions.EncodeException;
import fr.radi3nt.networking.packets.buffer.serializers.PacketDataSerializerWriter;

public interface WritablePacketBuffer extends PacketBuffer {

    void write(PacketDataSerializerWriter writer) throws EncodeException;

}
