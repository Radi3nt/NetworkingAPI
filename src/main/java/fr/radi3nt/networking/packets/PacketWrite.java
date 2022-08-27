package fr.radi3nt.networking.packets;

import fr.radi3nt.networking.exceptions.EncodeException;
import fr.radi3nt.networking.packets.buffer.PacketBuffer;
import fr.radi3nt.networking.packets.buffer.WritablePacketBuffer;

public interface PacketWrite extends Packet {

    void write(WritablePacketBuffer packetBuffer) throws EncodeException;

}
