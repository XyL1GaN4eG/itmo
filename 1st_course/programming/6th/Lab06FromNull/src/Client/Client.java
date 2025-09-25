package Client;

import General.ICommand;
import General.net.Request;
import Server.commandManager.commands.Info;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class Client {
    public static void main(String[] args) throws IOException {
        var port = 8009;
        var datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        var serverAdress = new InetSocketAddress("localhost", port);
        var command = new Request("info");

        datagramChannel.send()
    }
}
