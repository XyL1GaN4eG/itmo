package Server.commandManager.commands;

import General.LabWorkUtility;
import Server.commandManager.Command;
import Server.commandManager.CommandFactory;
import Server.commandManager.FileManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ExecuteScript extends Command {

    public ExecuteScript() {
        super(true);
    }

    @Override
    public String execute() {
        var filename = getArgument();
        String str = FileManager.readFromFile((String) filename, false);
        String[] parts = LabWorkUtility.customSplit(str, '\n');
        for (String element : parts) {
            try {
                commandStack.push(element); // Добавляем команду в стек
                if (executeLOG.contains(filename)) {
                    output = ("а можно без рекурсии пж....");
                    break;
                } else {
                    var splitted = LabWorkUtility.customSplit(element, ' ');
                    Command commanda = CommandFactory.createCommand(splitted[0]);
                    var myArgument = new ArrayList<String>();
                    myArgument.addAll(Arrays.asList(splitted).subList(1, splitted.length));

                    commanda.setArgument(myArgument);
                    commanda.execute();

                }
                commandStack.pop(); // После выполнения команды извлекаем ее из стека
            } catch (Exception e) {
                commandStack.pop(); // Извлекаем команду из стека, чтобы продолжить выполнение скрипта
            }
        }
        System.out.println(str);
        return "Команда execute исполнилась!";
    }


    @Override
    public String getName() {
        return "execute script";
    }

    private static final Stack<String> commandStack = new Stack<>();
    private final ArrayList<String> executeLOG = new ArrayList<>();

}
