package Server.rsc;

public class ServerGlobalVars {
    final public static String PATH_TO_SERVER = System.getenv("PWD") + "/out/production/Lab06/Server/";

    //путь к данным лабораторных работ

    final public static String PATH_TO_RSC = PATH_TO_SERVER + "rsc/";
    final public static String PATH_TO_DATA = PATH_TO_RSC + "data/";
    final public static String PATH_TO_CSV = PATH_TO_RSC + "data/dataLabWork.csv";

    //пакет с командами для PackageScanner
    final public static String packageCommandsName = "ServerMain.commandManager.commands";


    public static void main(String[] args) {
        System.out.println(PATH_TO_SERVER);
        System.out.println(PATH_TO_RSC);
        System.out.println(PATH_TO_DATA);
        System.out.println(PATH_TO_CSV);
    }



}
