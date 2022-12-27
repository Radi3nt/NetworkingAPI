package fr.radi3nt.networking.packets;

import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;

public interface PacketRead extends Packet {

    void read(ReadablePacketBuffer packetBuffer);

}
