package fr.radi3nt.networking.packets.buffer;

import fr.radi3nt.networking.packets.buffer.serializers.PacketDataSerializerReader;

import java.io.IOException;
import java.io.OutputStream;

public interface ReadablePacketBuffer extends PacketBuffer {

    void writeTo(OutputStream outputStream) throws IOException;
    void read(PacketDataSerializerReader serializerReader) throws IOException;

}
