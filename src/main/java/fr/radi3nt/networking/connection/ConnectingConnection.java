package fr.radi3nt.networking.connection;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.NetworkHolder;
import fr.radi3nt.networking.network.holder.ConnectingSocketNetworkHolder;
import fr.radi3nt.networking.network.socket.SocketAddress;
import fr.radi3nt.networking.packets.listener.PacketListener;
import fr.radi3nt.networking.protocol.PacketProtocol;

public class ConnectingConnection extends InteractiveConnection {

    private final PacketProtocol packetProtocol;
    private final NetworkHolder networkHolder;

    public ConnectingConnection(SocketAddress socketAddress, PacketProtocol packetProtocol, PacketListener packetListener) throws NetworkException {
        super(packetListener);
        this.packetProtocol = packetProtocol;
        this.networkHolder = new ConnectingSocketNetworkHolder(socketAddress);

        init();
        networkHolder.start();
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
        return networkHolder;
    }
}
