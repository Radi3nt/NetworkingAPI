package fr.radi3nt.networking.connection;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.NetworkHolder;
import fr.radi3nt.networking.network.NetworkTunnel;
import fr.radi3nt.networking.network.listener.TunnelListener;
import fr.radi3nt.networking.packets.PacketWrite;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;
import fr.radi3nt.networking.protocol.PacketProtocol;

public abstract class AbstractConnection implements Connection {

    protected NetworkHolder networkHolder;
    protected PacketProtocol packetProtocol;
    protected TunnelListener tunnelListener;

    public AbstractConnection() {

    }

    protected void init() {
        this.networkHolder = createNetworkHolder();
        this.packetProtocol = createPacketProtocol();
        this.tunnelListener = createTunnelListener();
    }

    public abstract void attachListener();

    protected abstract TunnelListener createTunnelListener();
    protected abstract PacketProtocol createPacketProtocol();
    protected abstract NetworkHolder createNetworkHolder();

    @Override
    public void sendPacket(PacketWrite... packets) throws NetworkException {
        if (networkHolderInValidState()) {
            ReadablePacketBuffer packetBuffer = packetProtocol.write(packets);
            networkHolder.getTunnel().write(packetBuffer);
        } else {
            throw new NetworkException("Network state is invalid");
        }
    }

    private boolean networkHolderInValidState() {
        return networkHolder.isOpened() && !networkHolder.isClosed();
    }

}
