package commandManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static commandManager.commands.LabWorkUtility.customSplit;

public class CommandExecutor {
    public void startExecuting() {
//        switch (commandMode) {
//            case CLI_UserMode -> {
        // Интерактивный режим
        Scanner in = new Scanner(System.in);
        try {
//                    String str = "";
            var command = new CommandManager(CommandMode.CLI_UserMode);
            while (true) {
                try {
//                            str = in.nextLine();
                    command.executeCommand(customSplit(in.nextLine(), ' '));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Введите хоть какую-то команду");
                }
            }
        } catch (NoSuchElementException ex) {
            System.out.println("Конец ввода");
            in.close();
        }
    }
//            case NonUserMode -> {

//            }
}
//    }


//            }