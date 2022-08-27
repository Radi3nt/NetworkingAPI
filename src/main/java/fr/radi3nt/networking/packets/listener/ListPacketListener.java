package fr.radi3nt.networking.packets.listener;

import fr.radi3nt.networking.packets.PacketRead;

import java.util.Collection;

public class ListPacketListener implements PacketListener {

    private final Collection<PacketListener> packetListeners;

    public ListPacketListener(Collection<PacketListener> packetListeners) {
        this.packetListeners = packetListeners;
    }

    public Collection<PacketListener> getPacketListeners() {
        return packetListeners;
    }

    @Override
    public void receive(PacketRead packetRead) {
        for (PacketListener packetListener : getPacketListeners()) {
            packetListener.receive(packetRead);
        }
    }
}
