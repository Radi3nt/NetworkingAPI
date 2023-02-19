package fr.radi3nt.networking.connection;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.NetworkHolder;
import fr.radi3nt.networking.network.holder.ReadySocketNetworkHolder;
import fr.radi3nt.networking.network.socket.SocketNetworkTunnel;
import fr.radi3nt.networking.protocol.PacketProtocol;

import java.net.Socket;

public class ServerConnection extends InteractiveConnection {

    private final PacketProtocol packetProtocol;
    private final Socket socket;

    public ServerConnection(PacketProtocol packetProtocol, Socket socket) {
        this.packetProtocol = packetProtocol;
        this.socket = socket;

        init();
    }

    public void close() throws NetworkException {
        networkHolder.stop();
    }

    @Override
    protected PacketProtocol createPacketProtocol() {
        return packetProtocol;
    }

    @Override
    protected NetworkHolder createNetworkHolder() {
        return new ReadySocketNetworkHolder(new SocketNetworkTunnel(socket));
    }

    public boolean isStillOpen() {
        return !networkHolder.isClosed();
    }
}
