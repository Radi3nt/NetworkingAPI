package fr.radi3nt.networking.packets;

import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

import java.io.IOException;

public interface PacketRead extends Packet {

    void read(ReadablePacketBuffer packetBuffer) throws IOException;

}
