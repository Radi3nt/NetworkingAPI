package fr.radi3nt.networking.connection;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.listener.ProtocolTunnelListener;
import fr.radi3nt.networking.network.listener.TunnelListener;
import fr.radi3nt.networking.packets.listener.ListPacketListener;

public abstract class InteractiveConnection extends AbstractConnection {

    protected final ListPacketListener listener = new ListPacketListener();

    @Override
    public void attachListener() {
        tunnelListener.attach(listener);
    }

    @Override
    public void attachSender() {
        while (!networkHolder.isClosed()) {
            try {
                sendPackets();
            } catch (NetworkException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected TunnelListener createTunnelListener() {
        return new ProtocolTunnelListener(networkHolder, packetProtocol);
    }

    public ListPacketListener getListener() {
        return listener;
    }
}
