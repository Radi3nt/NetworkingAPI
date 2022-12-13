package fr.radi3nt.networking.connection.handler;

import fr.radi3nt.networking.connection.ServerConnection;
import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.protocol.PacketProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerConnectionHandler implements ServerConnectionHandler {

    private final PacketProtocol packetProtocol;
    private final ServerSocket serverSocket;

    public SocketServerConnectionHandler(PacketProtocol packetProtocol, int port) throws NetworkException {
        this.packetProtocol = packetProtocol;
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
            return new ServerConnection(packetProtocol, socket);
        } catch (IOException e) {
            throw new NetworkException(e);
        }
    }
}
