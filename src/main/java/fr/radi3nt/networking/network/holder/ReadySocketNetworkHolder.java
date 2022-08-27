package fr.radi3nt.networking.network.holder;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.NetworkHolder;
import fr.radi3nt.networking.network.NetworkTunnel;
import fr.radi3nt.networking.network.socket.SocketAddress;
import fr.radi3nt.networking.network.socket.SocketNetworkTunnel;

import java.io.IOException;
import java.net.Socket;

public class ReadySocketNetworkHolder implements NetworkHolder {

    private final SocketNetworkTunnel networkTunnel;

    private final boolean opened;
    private boolean closed;

    public ReadySocketNetworkHolder(SocketNetworkTunnel networkTunnel) {
        this.networkTunnel = networkTunnel;
        this.opened = true;
    }

    @Override
    public void start() throws NetworkException {

    }

    @Override
    public void stop() throws NetworkException {
        closed = true;
        try {
            networkTunnel.close();
        } catch (IOException e) {
            throw new NetworkException(e);
        }
    }

    @Override
    public NetworkTunnel getTunnel() {
        return networkTunnel;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public boolean isOpened() {
        return opened;
    }
}
