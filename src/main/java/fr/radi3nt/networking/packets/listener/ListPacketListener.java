package fr.radi3nt.networking.packets.listener;

import fr.radi3nt.networking.packets.PacketRead;

import java.util.ArrayList;
import java.util.Collection;

public class ListPacketListener implements PacketListener {

    private final Collection<PacketListener> packetListeners;

    public ListPacketListener() {
        this(new ArrayList<>());
    }

    public ListPacketListener(Collection<PacketListener> packetListeners) {
        this.packetListeners = packetListeners;
    }

    public void add(PacketListener packetListener) {
        packetListeners.add(packetListener);
    }


    public void remove(PacketListener packetListener) {
        packetListeners.add(packetListener);
    }

    @Override
    public void receive(PacketRead packetRead) {
        for (PacketListener packetListener : packetListeners) {
            packetListener.receive(packetRead);
        }
    }
}
