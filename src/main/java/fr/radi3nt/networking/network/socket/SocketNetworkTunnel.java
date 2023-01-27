package fr.radi3nt.networking.network.socket;

import fr.radi3nt.networking.network.StreamNetworkTunnel;
import fr.radi3nt.networking.network.listener.CloseListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketNetworkTunnel extends StreamNetworkTunnel {

    private final Socket socket;
    private CloseListener closeListener;

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
        if (closeListener!=null)
            closeListener.connectionClosed();
    }

    @Override
    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }
}
