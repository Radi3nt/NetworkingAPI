package fr.radi3nt.networking.protocol.id;

import fr.radi3nt.networking.packets.PacketRead;
import fr.radi3nt.networking.packets.PacketWrite;

import java.util.Map;

public class PacketFactoryProtocolIdentification implements PacketProtocolIdentification {

    private final Map<Integer, PacketFactory> packetFactories;

    public PacketFactoryProtocolIdentification(Map<Integer, PacketFactory> packetFactories) {
        this.packetFactories = packetFactories;
    }

    @Override
    public int toPacketId(PacketWrite packetWrite) {
        for (Map.Entry<Integer, PacketFactory> integerPacketFactoryEntry : packetFactories.entrySet()) {
            PacketFactory factory = integerPacketFactoryEntry.getValue();
            int id = integerPacketFactoryEntry.getKey();

            if (factory.isPacket(packetWrite)) {
                return id;
            }
        }

        throw new IllegalArgumentException("Packet is not registered");
    }

    @Override
    public PacketRead fromPacketId(int id) {
        PacketFactory packetFactory = packetFactories.get(id);
        if (packetFactory!=null) {
            return packetFactory.create();
        }

        throw new IllegalArgumentException("Packet ID is not registered: " + id);
    }
}
