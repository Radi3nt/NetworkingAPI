package fr.radi3nt.networking.network;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.writer.NetworkWriter;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface OutputStreamNetworkTunnel extends NetworkTunnel {

    @Override
    default void write(ReadablePacketBuffer readablePacketBuffer) throws NetworkException {
        getNetworkWriter().write(readablePacketBuffer);
    }

    NetworkWriter getNetworkWriter();

}
