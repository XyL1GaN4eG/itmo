package General;

import Client.main.messages.RequestWithLabwork;
import Client.main.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPConnection {
    private final int bufferSize;
    private DatagramChannel channel;
    private final InetSocketAddress serverAddress;

    public UDPConnection(int bufferSize, InetAddress address, int port) throws IOException {
        this.bufferSize = bufferSize;
        this.channel = DatagramChannel.open();
        this.channel.configureBlocking(false);
        this.serverAddress = new InetSocketAddress(address, port);
    }

    public void connect(String serverIP, int serverPort) throws IOException {
        channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(serverIP, serverPort));
    }

    public void close() throws IOException {
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
    }

    public void sendRequest(RequestWithLabwork request) throws IOException {
        if (channel != null && channel.isOpen()) {
            var buffer = ByteBuffer.allocate(bufferSize);
            buffer.clear();
            buffer.put(serialize(request));
            buffer.flip();
            channel.send(buffer, serverAddress);
        }
    }

    public Response recieve() throws IOException {
        if (channel != null && channel.isOpen()) {
            var buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);

            //buffer.flip меняет с getting`а на setting функционал буфера
            buffer.flip();

            return deserialize(buffer.array());
        }

        throw new IOException("Соединение не установлено");
    }

    private byte[] serialize(RequestWithLabwork request) throws IOException {
        try (var byteArrayOutputStream = new ByteArrayOutputStream();
        var objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(request);
            return byteArrayOutputStream.toByteArray();
        }
    }

    private Response deserialize(byte[] bytes) throws IOException {
        var byteArrayInputStream = new ByteArrayInputStream(bytes);
        var objectInputStream = new ObjectInputStream(byteArrayInputStream);
        try {
            return (Response) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
