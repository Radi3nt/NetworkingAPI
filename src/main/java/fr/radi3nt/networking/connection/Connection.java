package fr.radi3nt.networking.connection;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.packets.PacketWrite;

public interface Connection {

    void sendPacket(PacketWrite... packets) throws NetworkException;

}
