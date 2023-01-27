package fr.radi3nt.networking.network.listener;

import fr.radi3nt.networking.exceptions.ConnectionClosedException;
import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.NetworkHolder;
import fr.radi3nt.networking.packets.PacketRead;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;
import fr.radi3nt.networking.packets.listener.PacketListener;
import fr.radi3nt.networking.protocol.PacketProtocol;

public class ProtocolTunnelListener implements TunnelListener {

    private final NetworkHolder networkHolder;
    private final PacketProtocol packetProtocol;

    public ProtocolTunnelListener(NetworkHolder networkHolder, PacketProtocol packetProtocol) {
        this.networkHolder = networkHolder;
        this.packetProtocol = packetProtocol;
    }

    @Override
    public void attach(PacketListener listener) {
        while (!networkHolder.isClosed()) {
            try {
                readPackets(listener);
            } catch (ConnectionClosedException closedException) {
                closeNetwork();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void closeNetwork() {
        try {
            networkHolder.stop();
        } catch (NetworkException e) {
            e.printStackTrace();
        }
    }

    private void readPackets(PacketListener listener) throws NetworkException {
        ReadablePacketBuffer readablePacketBuffer = networkHolder.getTunnel().read();
        if (readablePacketBuffer != null) {
            PacketRead[] packetReads = packetProtocol.read(readablePacketBuffer);
            for (PacketRead packetRead : packetReads) {
                listener.receive(packetRead);
            }
        }
    }

}
