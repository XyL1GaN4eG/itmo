//package General.net;
//
//import java.io.*;
//import java.net.InetAddress;
//import java.net.InetSocketAddress;
//import java.net.PortUnreachableException;
//import java.net.SocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.DatagramChannel;
//
//public class UDPConnection {
//    private final int bufferSize;
//    private DatagramChannel channel;
//    private final InetSocketAddress serverAddress;
//
//    public UDPConnection(int bufferSize, InetAddress address, int port) throws IOException {
//        this.bufferSize = bufferSize;
//        this.channel = DatagramChannel.open();
//        this.channel.configureBlocking(false); // Устанавливаем неблокирующий режим
//        this.serverAddress = new InetSocketAddress(address, port);
//    }
//
//    public void connect(String serverIP, int serverPort) throws IOException {
//        channel = DatagramChannel.open();
//        channel.configureBlocking(false); // Устанавливаем неблокирующий режим
//        channel.connect(new InetSocketAddress(serverIP, serverPort));
//    }
//
//    public void close() throws IOException {
//        if (channel != null && channel.isOpen()) {
//            channel.close();
//        }
//    }
//
//    public void sendMessage(IMessage request) throws IOException {
//        if (channel != null && channel.isOpen()) {
//            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
//            buffer.clear();
//            buffer.put(serialize(request));
//            buffer.flip();
//            // Клиент отправляет сообщение на серверный адрес
//            if (channel.isConnected()) {
//                channel.write(buffer);
//            } else {
//                channel.send(buffer, serverAddress);
//            }
//        }
//    }
//
//
//    public IMessage receive() throws IOException {
//        if (channel != null && channel.isOpen()) {
//            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
//            SocketAddress remoteAddress = null; // Используем receive для неблокирующего чтения
//            try {
//                remoteAddress = channel.receive(buffer);
//            } catch (PortUnreachableException e) {
//                e.printStackTrace();
//            }
//
//            buffer.flip(); // Переключаемся на чтение из буфера
//            if (remoteAddress != null) {
//                return (IMessage) deserialize(buffer.array());
//            }
//            return null;
//        }
//
//        throw new IOException("Соединение не установлено");
//    }
//
//    public byte[] serialize(IMessage message) throws IOException {
//        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
//            objectOutputStream.writeObject(message);
//            return byteArrayOutputStream.toByteArray();
//        }
//    }
//
//    public Serializable deserialize(byte[] bytes) throws IOException {
//        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
//            return (Serializable) objectInputStream.readObject();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
