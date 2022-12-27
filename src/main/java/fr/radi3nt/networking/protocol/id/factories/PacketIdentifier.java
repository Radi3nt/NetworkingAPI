package fr.radi3nt.networking.protocol.id.factories;

import fr.radi3nt.networking.packets.PacketWrite;

public interface PacketIdentifier {

    boolean isPacket(PacketWrite packet);

}
