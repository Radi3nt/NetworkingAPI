package fr.radi3nt.networking.network.holder;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.NetworkHolder;
import fr.radi3nt.networking.network.NetworkTunnel;
import fr.radi3nt.networking.network.socket.SocketAddress;
import fr.radi3nt.networking.network.socket.SocketNetworkTunnel;

import java.io.IOException;
import java.net.Socket;

public class ConnectingSocketNetworkHolder implements NetworkHolder {

    private final SocketAddress socketAddress;
    private SocketNetworkTunnel networkTunnel;

    private boolean opened;
    private boolean closed;

    public ConnectingSocketNetworkHolder(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    @Override
    public void start() throws NetworkException {
        try {
            networkTunnel = new SocketNetworkTunnel(new Socket(socketAddress.getHostName(), socketAddress.getPort()));
        } catch (IOException e) {
            throw new NetworkException(e);
        }
        opened = true;
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
