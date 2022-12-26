package fr.radi3nt.networking.connection;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.NetworkHolder;
import fr.radi3nt.networking.network.listener.TunnelListener;
import fr.radi3nt.networking.packets.PacketWrite;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;
import fr.radi3nt.networking.protocol.PacketProtocol;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractConnection implements Connection {

    protected NetworkHolder networkHolder;
    protected PacketProtocol packetProtocol;
    protected TunnelListener tunnelListener;

    protected Queue<PacketWrite> packetWrites = new ConcurrentLinkedQueue<>();

    public AbstractConnection() {

    }

    protected void init() {
        this.networkHolder = createNetworkHolder();
        this.packetProtocol = createPacketProtocol();
        this.tunnelListener = createTunnelListener();
    }

    public abstract void attachListener();
    public abstract void attachSender();

    protected abstract TunnelListener createTunnelListener();
    protected abstract PacketProtocol createPacketProtocol();
    protected abstract NetworkHolder createNetworkHolder();

    @Override
    public void sendPacket(PacketWrite... packets) throws NetworkException {
        packetWrites.addAll(Arrays.asList(packets));
    }

    public void sendPackets() throws NetworkException {
        if (networkHolderInValidState()) {
            while (!packetWrites.isEmpty()) {
                PacketWrite packets = packetWrites.poll();
                ReadablePacketBuffer packetBuffer = packetProtocol.write(packets);
                networkHolder.getTunnel().write(packetBuffer);
            }
        } else {
            throw new NetworkException("Network state is invalid");
        }
    }

    private boolean networkHolderInValidState() {
        return networkHolder.isOpened() && !networkHolder.isClosed();
    }
}
