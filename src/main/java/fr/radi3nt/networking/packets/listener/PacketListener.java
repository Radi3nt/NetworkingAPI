package fr.radi3nt.networking.packets.listener;

import fr.radi3nt.networking.packets.PacketRead;

public interface PacketListener {

    void receive(PacketRead packetRead);

}
