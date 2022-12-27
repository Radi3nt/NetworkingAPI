package fr.radi3nt.networking.protocol;

import fr.radi3nt.networking.packets.PacketRead;
import fr.radi3nt.networking.packets.PacketWrite;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

public interface PacketProtocol {

    ReadablePacketBuffer write(PacketWrite... packetWrite);
    PacketRead[] read(ReadablePacketBuffer source);

}
