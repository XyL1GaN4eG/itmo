package Server.net;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        SetupServer.getLabsFromFile();
        // Создаем и запускаем серверный поток
        Server server = new Server();
        server.run();
    }
}
