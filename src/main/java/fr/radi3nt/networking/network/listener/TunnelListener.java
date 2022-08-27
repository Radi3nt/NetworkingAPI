package fr.radi3nt.networking.network.listener;

import fr.radi3nt.networking.packets.listener.PacketListener;

public interface TunnelListener {

    void attach(PacketListener listener);

}
