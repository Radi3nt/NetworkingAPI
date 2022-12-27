package fr.radi3nt.networking.protocol.id.factories;

import fr.radi3nt.networking.packets.PacketWrite;

public class ReflectionPacketIdentifier implements PacketIdentifier {

    private final Class<? extends PacketWrite> aClass;

    public ReflectionPacketIdentifier(Class<? extends PacketWrite> aClass) {
        this.aClass = aClass;
    }

    @Override
    public boolean isPacket(PacketWrite packet) {
        return packet.getClass().equals(aClass);
    }
}
