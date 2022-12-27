package fr.radi3nt.networking.network.writer;

import fr.radi3nt.networking.exceptions.NetworkException;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

public interface NetworkWriter {

    void write(ReadablePacketBuffer buffer) throws NetworkException;

}
