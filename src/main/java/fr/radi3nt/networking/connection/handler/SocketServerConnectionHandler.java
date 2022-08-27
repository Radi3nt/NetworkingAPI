package fr.radi3nt.networking.connection.handler;

import fr.radi3nt.networking.connection.Connection;
import fr.radi3nt.networking.connection.ServerConnection;
import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.packets.listener.ListPacketListener;
import fr.radi3nt.networking.packets.listener.PacketListener;
import fr.radi3nt.networking.protocol.PacketProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class SocketServerConnectionHandler implements ServerConnectionHandler {

    private final PacketProtocol packetProtocol;
    private final PacketListenerProvider packetListenerProvider;
    private final ServerSocket serverSocket;

    public SocketServerConnectionHandler(PacketProtocol packetProtocol, PacketListenerProvider packetListenerProvider, int port) throws NetworkException {
        this.packetProtocol = packetProtocol;
        this.packetListenerProvider = packetListenerProvider;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new NetworkException(e);
        }
    }

    @Override
    public ServerConnection accept() throws NetworkException {
        try {
            Socket socket = serverSocket.accept();
            Collection<PacketListener> packetListeners = new ArrayList<>();
            ServerConnection serverConnection = new ServerConnection(packetProtocol, socket, new ListPacketListener(packetListeners));

            packetListeners.add(packetListenerProvider.provide(serverConnection));

            return serverConnection;
        } catch (IOException e) {
            throw new NetworkException(e);
        }
    }
}
