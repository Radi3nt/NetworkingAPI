package fr.radi3nt.networking.protocol.id;

import fr.radi3nt.networking.packets.PacketRead;
import fr.radi3nt.networking.packets.PacketWrite;

public interface PacketProtocolIdentification {

    int toPacketId(PacketWrite packetRead);
    PacketRead fromPacketId(int id);

}
