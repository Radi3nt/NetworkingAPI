package fr.radi3nt.networking.network;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.reader.NetworkReader;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

public interface InputStreamNetworkTunnel extends NetworkTunnel {

    @Override
    default ReadablePacketBuffer read() throws NetworkException {
        ReadablePacketBuffer packetBuffer = getNetworkReader().poll();
        if (packetBuffer!=null)
            return packetBuffer;

        getNetworkReader().read();

        return getNetworkReader().poll();
    }

    NetworkReader getNetworkReader();
}
