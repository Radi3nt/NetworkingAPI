package fr.radi3nt.networking.protocol.id;

import fr.radi3nt.networking.packets.Packet;
import fr.radi3nt.networking.packets.PacketRead;

public interface PacketFactory {

    boolean isPacket(Packet packet);
    PacketRead create();

}
