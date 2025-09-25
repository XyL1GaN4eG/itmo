package Server.net;

public class Server {
    public static void main(String[] args) {
        SetupServer.getLabsFromFile();
        // Создаем и запускаем серверный поток
        Serv serv = new Serv();
        serv.run();
    }
}
