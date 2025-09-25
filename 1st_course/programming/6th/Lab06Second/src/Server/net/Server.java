package Server.net;

import General.net.Request;
import General.net.Response;
import Server.commandManager.CommandManager;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server extends Thread {
    private int port = 50457;
    private DatagramSocket socket;
    private boolean isRunning;
    private static final int BUFFER_SIZE = 4096;
    private static final int HEADER_SIZE = 4;
    private ExecutorService threadPool;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
        System.out.println("Finished");
    }

    public Server() {
        try {
            socket = new DatagramSocket(port);
            setDaemon(true);
            threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {

        isRunning = true;
        var commandManager = new CommandManager();
        byte[] inBuffer = new byte[BUFFER_SIZE];

        // Main server loop
        while (isRunning) {
                DatagramPacket packet = new DatagramPacket(inBuffer, inBuffer.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            InetAddress address = packet.getAddress();
                int port = packet.getPort();

            threadPool.submit(() -> {
                try {
//                    DatagramPacket packet = new DatagramPacket(inBuffer, inBuffer.length);
//                    socket.receive(packet);
//
//                    InetAddress address = packet.getAddress();
//                    int port = packet.getPort();
//
////                    ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
////                    ObjectInputStream ois = new ObjectInputStream(bais);
////                    Request request = (Request) ois.readObject();
////                    ois.close();
//
//                    Response initialResponse = new Response(commandManager.getInfo());
//                    sendResponse(address, initialResponse, port);
//                    while(true) {
                        handleRequest(packet, address, port, commandManager);
//                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void handleRequest
            (DatagramPacket packet, InetAddress address, int port, CommandManager commandManager)
            throws ClassNotFoundException, IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Request request = (Request) ois.readObject();
        ois.close();
        if (Objects.equals(request.getName(), "i")) {
            sendResponse(address, new Response(commandManager.getInfo()), port);
        } else {
            Object data = commandManager.executeCommand(request.getData());
            Response response = new Response(data);
            sendResponse(address, response, port);
        }
    }

    public void sendResponse(InetAddress address, Response response, int sendPort) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(response);
        oos.flush();

        byte[] responseBytes = baos.toByteArray();
        int numPackets = (int) Math.ceil((double) responseBytes.length / (BUFFER_SIZE - HEADER_SIZE));

        // Send number of packets
        byte[] numPacketsBuffer = ByteBuffer.allocate(HEADER_SIZE).putInt(numPackets).array();
        socket.send(new DatagramPacket(numPacketsBuffer, numPacketsBuffer.length, address, sendPort));


        for (int i = 0; i < numPackets; i++) {
            int start = i * (BUFFER_SIZE - HEADER_SIZE);
            int length = Math.min(BUFFER_SIZE - HEADER_SIZE, responseBytes.length - start);

            byte[] packetData = new byte[length + HEADER_SIZE];
            System.arraycopy(responseBytes, start, packetData, HEADER_SIZE, length);
            byte[] packetIndex = ByteBuffer.allocate(HEADER_SIZE).putInt(i).array();
            System.arraycopy(packetIndex, 0, packetData, 0, HEADER_SIZE);

            DatagramPacket responsePacket = new DatagramPacket(packetData, packetData.length, address, sendPort);
            socket.send(responsePacket);
        }

        baos.close();
        oos.close();
    }
}
