package fr.radi3nt.networking.connection.handler;

import fr.radi3nt.networking.connection.ServerConnection;
import fr.radi3nt.networking.packets.listener.PacketListener;

public interface PacketListenerProvider {

    PacketListener provide(ServerConnection serverConnection);

}
