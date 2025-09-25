package Server;

import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;

import static Server.GlobalVars.PATH_TO_COMMANDS;
import static Server.GlobalVars.packageCommandsName;
import static Server.SetupServer.getLabsFromFile;
import static Server.commandManager.PackageScanner.findClassesInDirectory;

public class Server {
    private static final int SERVER_PORT = 3000;

    public static void main(String[] args) throws Exception {

        getLabsFromFile();


        //переменная, которую мы отсылаем клиенту, чтобы он мог провалидировать у себя команду
        HashMap<String, Boolean[]> infoAboutCommands = findClassesInDirectory(packageCommandsName, PATH_TO_COMMANDS);

        // datagramChan
        // передает информацию в датаграмах (типо буфер)
        var datagramChan = DatagramChannel.open();

        // включаем неблокирующий режим
        datagramChan.configureBlocking(false);

        ByteBuffer buffer = null;



    }
}
