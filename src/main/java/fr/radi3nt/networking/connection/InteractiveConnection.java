package fr.radi3nt.networking.connection;

import fr.radi3nt.networking.network.listener.ProtocolTunnelListener;
import fr.radi3nt.networking.network.listener.TunnelListener;
import fr.radi3nt.networking.packets.listener.PacketListener;

public abstract class InteractiveConnection extends AbstractConnection {

    protected final PacketListener listener;

    public InteractiveConnection(PacketListener packetListener) {
        listener = packetListener;
    }

    @Override
    public void attachListener() {
        tunnelListener.attach(listener);
    }

    @Override
    protected TunnelListener createTunnelListener() {
        return new ProtocolTunnelListener(networkHolder, packetProtocol);
    }

}
