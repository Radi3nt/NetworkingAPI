package fr.radi3nt.networking.connection;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.NetworkHolder;
import fr.radi3nt.networking.network.listener.CloseListener;
import fr.radi3nt.networking.network.listener.TunnelListener;
import fr.radi3nt.networking.packets.PacketWrite;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;
import fr.radi3nt.networking.protocol.PacketProtocol;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractConnection implements Connection {

    protected NetworkHolder networkHolder;
    protected PacketProtocol packetProtocol;
    protected TunnelListener tunnelListener;

    protected final Queue<PacketWrite> packetWrites = new ConcurrentLinkedQueue<>();
    protected boolean blocked = false;
    protected final Lock blockedLock = new ReentrantLock();

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
        if (!blocked) {
            synchronized (packetWrites) {
                packetWrites.addAll(Arrays.asList(packets));
                packetWrites.notifyAll();
            }
        }
    }

    public void sendPackets() {
        while (!networkHolder.isClosed()) {
            try {
                if (sendPacketsIfNotClosed()) {
                    break;
                }
            } catch (NetworkException e) {
                break;
            }
        }
        unblockWaitingForPacketsToBeSent();
    }

    private boolean sendPacketsIfNotClosed() throws NetworkException {
        if (networkHolderInValidState()) {
            while (!packetWrites.isEmpty()) {
                PacketWrite packets = packetWrites.poll();
                ReadablePacketBuffer packetBuffer = packetProtocol.write(packets);

                if (networkHolder.isClosed()) {
                    unblockWaitingForPacketsToBeSent();
                    return true;
                }

                networkHolder.getTunnel().write(packetBuffer);
            }
            waitUntilPacketWritesNotEmpty();
            if (blocked)
                unblockWaitingForPacketsToBeSent();
        } else {
            unblockWaitingForPacketsToBeSent();
            throw new NetworkException("Network state is invalid");
        }
        return false;
    }

    private void waitUntilPacketWritesNotEmpty() {
        synchronized (packetWrites) {
            if (blocked)
                return;
            if (!packetWrites.isEmpty())
                return;
            try {
                packetWrites.wait(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void unblockWaitingForPacketsToBeSent() {
        synchronized (blockedLock) {
            blockedLock.notifyAll();
        }
    }

    public void blockNewPacketsAndWaitTillAllSentToClose() throws InterruptedException, NetworkException {
        blocked = true;
        synchronized (packetWrites) {
            packetWrites.notifyAll();
        }
        synchronized (blockedLock) {
            blockedLock.wait();
        }
        this.networkHolder.stop();
    }

    public void setCloseListener(CloseListener closeListener) {
        this.networkHolder.getTunnel().setCloseListener(closeListener);
    }

    private boolean networkHolderInValidState() {
        return networkHolder.isOpened() && !networkHolder.isClosed();
    }

    @Override
    public boolean isClosed() {
        return networkHolder.isClosed();
    }
}
