package fr.radi3nt.networking.network.socket;

public class SocketAddress {

    private final String hostName;
    private final int port;

    public SocketAddress(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

}
