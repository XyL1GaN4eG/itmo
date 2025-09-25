package Server.rsc;

public class ServerGlobalVars {
    final private static String PATH_TO_SERVER = System.getenv("PWD") + "/out/production/Lab06/Server/";

    //путь к данным лабораторных работ
    final public static String PATH_TO_CSV = PATH_TO_SERVER + "rsc/data/dataLabWork.csv";

    final public static String PATH_TO_RSC = PATH_TO_SERVER + "rsc/";

    final public static String PATH_TO_COMMANDS = PATH_TO_SERVER + "commandManager/commands";

    //пакет с командами для PackageScanner
    final public static String packageCommandsName = "Server.commandManager.commands";


    public static void main(String[] args) {
        System.out.println(PATH_TO_COMMANDS);
    }



}
