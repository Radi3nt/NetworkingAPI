package fr.radi3nt.networking.network;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.network.listener.CloseListener;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

public interface NetworkTunnel {

    void write(ReadablePacketBuffer readablePacketBuffer) throws NetworkException;
    ReadablePacketBuffer read() throws NetworkException;

    void setCloseListener(CloseListener closeListener);

}
