package fr.radi3nt.networking.network;

import fr.radi3nt.networking.network.reader.NetworkReader;
import fr.radi3nt.networking.network.reader.NonBlockingNetworkReader;
import fr.radi3nt.networking.network.writer.NetworkWriter;
import fr.radi3nt.networking.network.writer.SizingNetworkWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class StreamNetworkTunnel implements NetworkTunnel, InputStreamNetworkTunnel, OutputStreamNetworkTunnel {

    private NetworkReader reader;
    private NetworkWriter writer;

    protected void init() {
        try {
            reader = new NonBlockingNetworkReader(getOriginalInputStream());
            writer = new SizingNetworkWriter(getOriginalOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract InputStream getOriginalInputStream() throws IOException;
    protected abstract OutputStream getOriginalOutputStream() throws IOException;

    @Override
    public NetworkReader getNetworkReader() {
        return reader;
    }

    @Override
    public NetworkWriter getNetworkWriter() {
        return writer;
    }
}
