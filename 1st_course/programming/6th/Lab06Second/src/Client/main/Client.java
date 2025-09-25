package Client.main;

import General.dataClasses.TypesOfArguments;
import General.net.Request;
import General.net.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
    private int port;
    private byte[] buffer = new byte[4096];
    private InetAddress address;
    DatagramSocket socket;

    final int serverPort = 50457;

    public Client() {
        try {
            address = InetAddress.getLocalHost();
            socket = new DatagramSocket((int) (Math.random() * 1000 + 5000));
        } catch (UnknownHostException | SocketException e) {
            System.err.println(e.getMessage());
        }
    }

    public Object sendRequest(Request request) {
        try {
            socket.connect(InetAddress.getLocalHost(), serverPort);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(request);
            oos.flush();

            byte[] requestBytes = baos.toByteArray();
            DatagramPacket datagramPacket = new DatagramPacket(requestBytes, requestBytes.length, socket.getInetAddress(), serverPort);
            socket.send(datagramPacket);

            baos.reset();
            oos.reset();
            baos.close();
            oos.close();

            Object message = getResponse();
            socket.disconnect();
            return message;

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public Object getResponse() {
        Object message = "Ответ не получен";
        try {
            byte[] headerBuffer = new byte[4];
            DatagramPacket headerPacket = new DatagramPacket(headerBuffer, headerBuffer.length);
            socket.receive(headerPacket);
            int numPackets = ByteBuffer.wrap(headerBuffer).getInt();

            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            for (int i = 0; i < numPackets; i++) {
                byte[] buffer = new byte[4096];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(responsePacket);

                int packetIndex = ByteBuffer.wrap(Arrays.copyOfRange(buffer, 0, 4)).getInt();
                responseStream.write(buffer, 4, responsePacket.getLength() - 4);
            }

            ByteArrayInputStream bais = new ByteArrayInputStream(responseStream.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Response response = (Response) ois.readObject();
            message = response.getData();

            ois.close();
            bais.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return message;
    }

    public static void main(String[] args) {
        Client client = new Client();
        var in = new Scanner(System.in);
        Request request;
        HashMap<String, TypesOfArguments> collection;

        // Initial request to get collection for validation
        request = new Request();
        collection = (HashMap<String, TypesOfArguments>) client.sendRequest(request);
        System.out.println(collection);

        while (true) {
            request = Validation.validate(collection);
            System.out.println(client.sendRequest(request));
        }
    }
}
