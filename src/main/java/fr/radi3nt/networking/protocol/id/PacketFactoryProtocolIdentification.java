package fr.radi3nt.networking.protocol.id;

import fr.radi3nt.networking.packets.PacketRead;
import fr.radi3nt.networking.packets.PacketWrite;
import fr.radi3nt.networking.protocol.id.factories.PacketFactory;
import fr.radi3nt.networking.protocol.id.factories.PacketIdentifier;

import java.util.Map;

public class PacketFactoryProtocolIdentification implements PacketProtocolIdentification {

    private final Map<Integer, PacketFactory> packetFactories;
    private final Map<Integer, PacketIdentifier> packetIdentifiers;

    public PacketFactoryProtocolIdentification(Map<Integer, PacketFactory> packetFactories, Map<Integer, PacketIdentifier> packetIdentifiers) {
        this.packetFactories = packetFactories;
        this.packetIdentifiers = packetIdentifiers;
    }

    @Override
    public int toPacketId(PacketWrite packetWrite) {
        for (Map.Entry<Integer, PacketIdentifier> integerPacketFactoryEntry : packetIdentifiers.entrySet()) {
            PacketIdentifier identifier = integerPacketFactoryEntry.getValue();
            int id = integerPacketFactoryEntry.getKey();

            if (identifier.isPacket(packetWrite)) {
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
