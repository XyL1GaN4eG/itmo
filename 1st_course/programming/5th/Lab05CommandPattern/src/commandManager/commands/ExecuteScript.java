package commandManager.commands;

import commandManager.Command;
import commandManager.CommandManager;
import commandManager.CommandMode;

import java.util.ArrayList;
import java.util.Stack;

public class ExecuteScript extends Command {

    public ExecuteScript() {
        super(false);
    }

    @Override
    public void execute() {
        var filename = getArgument();
        String str = FileManager.readFromFile((String) filename, false);
        String[] parts = LabWorkUtility.customSplit(str, '\n');
        var commandExecutor = new CommandManager(CommandMode.NonUserMode);
        for (String element : parts) {
            try {
                commandStack.push(element); // Добавляем команду в стек
                if (executeLOG.contains(filename)) {
                    System.out.println("а можно без рекурсии пж....");
                    break;
                } else {
                    commandExecutor.executeCommand(LabWorkUtility.customSplit(element, ' '));
                }
                commandStack.pop(); // После выполнения команды извлекаем ее из стека
            } catch (Exception e) {
//                System.out.println("Ошибка при выполнении команды: " + e.getMessage());
                commandStack.pop(); // Извлекаем команду из стека, чтобы продолжить выполнение скрипта
            }
        }
    }

//    @Override
//    public boolean checkArgument(String argument) {
//        return true;
//    }

    @Override
    public String getName() {
        return "execute script";
    }

    private final Stack<String> commandStack = new Stack<>();
    private final ArrayList<String> executeLOG = new ArrayList<>();
//    private String filename = "rsc/dataLabWork.csv";

}
