package fr.radi3nt.networking.protocol.id.factories;

import fr.radi3nt.networking.packets.Packet;
import fr.radi3nt.networking.packets.PacketRead;
import fr.radi3nt.networking.packets.PacketWrite;

public interface PacketFactory {

    PacketRead create();

}
