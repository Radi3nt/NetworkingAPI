package fr.radi3nt.networking.main;

import fr.radi3nt.networking.connection.Connection;
import fr.radi3nt.networking.connection.InteractiveConnection;
import fr.radi3nt.networking.connection.ServerConnection;
import fr.radi3nt.networking.connection.handler.PacketListenerProvider;
import fr.radi3nt.networking.connection.handler.SocketServerConnectionHandler;
import fr.radi3nt.networking.exceptions.EncodeException;
import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.NetworkHolder;
import fr.radi3nt.networking.network.holder.ConnectingSocketNetworkHolder;
import fr.radi3nt.networking.network.holder.ReadySocketNetworkHolder;
import fr.radi3nt.networking.network.socket.SocketAddress;
import fr.radi3nt.networking.network.socket.SocketNetworkTunnel;
import fr.radi3nt.networking.packets.PacketRead;
import fr.radi3nt.networking.packets.PacketWrite;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;
import fr.radi3nt.networking.packets.buffer.WritablePacketBuffer;
import fr.radi3nt.networking.packets.buffer.serializers.IntReader;
import fr.radi3nt.networking.packets.buffer.serializers.IntWriter;
import fr.radi3nt.networking.packets.listener.ListPacketListener;
import fr.radi3nt.networking.packets.listener.PacketListener;
import fr.radi3nt.networking.protocol.PacketProtocol;
import fr.radi3nt.networking.protocol.SizedIdPacketProtocol;
import fr.radi3nt.networking.protocol.id.PacketProtocolIdentification;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class MainNetworkingAPIDemoServer {

    public static void main(String[] args) throws NetworkException {
        SocketServerConnectionHandler socketServerConnectionHandler = createConnectionHandler();
        ServerConnection connection = socketServerConnectionHandler.accept();
        createListener(connection);

        sleepFor(10);

        MainNetworkingAPIDemoClient.TestPacketReadWrite closed = new MainNetworkingAPIDemoClient.TestPacketReadWrite();
        closed.number = 1381;
        connection.sendPacket(closed);

        connection.close();
    }

    private static void sleepFor(int seconds) {
        try {
            Thread.sleep(seconds*1_000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createListener(ServerConnection connection) {
        Thread thread = new Thread(connection::attachListener);
        thread.start();
    }

    private static SocketServerConnectionHandler createConnectionHandler() throws NetworkException {
        SizedIdPacketProtocol serverPacketProtocol = new SizedIdPacketProtocol(MainNetworkingAPIDemoClient.createIdentification());
        return new SocketServerConnectionHandler(serverPacketProtocol, serverConnection -> packetRead -> {
            System.out.println(packetRead);

            MainNetworkingAPIDemoClient.TestPacketReadWrite testPacketReadWrite = new MainNetworkingAPIDemoClient.TestPacketReadWrite();
            testPacketReadWrite.number = 4;
            try {
                serverConnection.sendPacket(testPacketReadWrite);
            } catch (NetworkException e) {
                throw new RuntimeException(e);
            }
        }, MainNetworkingAPIDemoClient.PORT);
    }

}
