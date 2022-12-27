package fr.radi3nt.networking.protocol;

import fr.radi3nt.networking.exceptions.EncodeException;
import fr.radi3nt.networking.packets.PacketRead;
import fr.radi3nt.networking.packets.PacketWrite;
import fr.radi3nt.networking.packets.buffer.PacketDataStreamBuffer;
import fr.radi3nt.networking.packets.buffer.ReadablePacketBuffer;
import fr.radi3nt.networking.packets.buffer.WritablePacketBuffer;
import fr.radi3nt.networking.packets.buffer.serializers.IntReader;
import fr.radi3nt.networking.packets.buffer.serializers.IntWriter;
import fr.radi3nt.networking.protocol.id.PacketProtocolIdentification;

public class SizedIdPacketProtocol implements PacketProtocol {

    private final PacketProtocolIdentification packetProtocolIdentification;

    private final IntWriter intWriter = new IntWriter(0);
    private final IntReader intReader = new IntReader();

    public SizedIdPacketProtocol(PacketProtocolIdentification packetProtocolIdentification) {
        this.packetProtocolIdentification = packetProtocolIdentification;
    }

    @Override
    public ReadablePacketBuffer write(PacketWrite... packetWrite) {
        PacketDataStreamBuffer buffer = new PacketDataStreamBuffer();

        for (PacketWrite write : packetWrite) {
            try {
                encodePacket(write, buffer);
            } catch (EncodeException e) {
                handleEncodeException(e);
            }
        }

        return buffer.toReadablePacketBuffer();
    }

    private void handleEncodeException(EncodeException e) {
        e.printStackTrace();
    }

    private void encodePacket(PacketWrite write, WritablePacketBuffer writablePacketBuffer) throws EncodeException {
        int packetId = packetProtocolIdentification.toPacketId(write);
        encodeInt(writablePacketBuffer, packetId);
        write.write(writablePacketBuffer);
    }

    private void encodeInt(WritablePacketBuffer writablePacketBuffer, int intToEncode) throws EncodeException {
        intWriter.setIntToEncode(intToEncode);
        writablePacketBuffer.write(intWriter);
    }

    @Override
    public PacketRead[] read(ReadablePacketBuffer source) {
        return new PacketRead[] {decodePacket(source)};
    }

    private PacketRead decodePacket(ReadablePacketBuffer source) {
        source.read(intReader);
        int packetId = intReader.getIntResult();
        PacketRead read = packetProtocolIdentification.fromPacketId(packetId);
        read.read(source);

        return read;
    }
}
