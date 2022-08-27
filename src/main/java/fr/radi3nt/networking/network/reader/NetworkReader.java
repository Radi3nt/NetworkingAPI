package fr.radi3nt.networking.network.reader;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

public interface NetworkReader {

    void read() throws NetworkException;
    ReadablePacketBuffer poll();

}
