package fr.radi3nt.networking.network.socket;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.StreamNetworkTunnel;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SocketNetworkTunnel extends StreamNetworkTunnel {

    private final Socket socket;

    public SocketNetworkTunnel(Socket socket) {
        this.socket = socket;
        init();
    }

    @Override
    protected InputStream getOriginalInputStream() throws IOException {
        return socket.getInputStream();
    }

    @Override
    protected OutputStream getOriginalOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public void close() throws IOException {
        socket.close();
    }
}
