package fr.radi3nt.networking.protocol.id.factories;

import fr.radi3nt.networking.packets.PacketRead;

public class ReflectionPacketFactory implements PacketFactory {

    private final Class<? extends PacketRead> aClass;

    public ReflectionPacketFactory(Class<? extends PacketRead> aClass) {
        this.aClass = aClass;
    }

    @Override
    public PacketRead create() {
        try {
            return aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
