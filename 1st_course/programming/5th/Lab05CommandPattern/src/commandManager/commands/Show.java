package commandManager.commands;

import commandManager.Command;
import dataClasses.LabWork;
import dataClasses.LabWorkSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static commandManager.commands.OutputFormatter.printData;
import static commandManager.commands.OutputFormatter.printHeader;
import static dataClasses.LabWorkSet.labWorks;

public class Show extends Command {

//    private Object;

    public Show() {
        super(true);
    }

    @Override
    public void execute() {
//        static void show(LinkedHashSet<LabWork> linkedHashSet) {
        List<Integer> maxLengths = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        List<String> headers = Arrays.asList(
                "id",
                "name",
                "coordinates_x",
                "coordinates_y",
                "creationDate",
                "minimalPoint",
                "difficulty",
                "author_name",
                "author_birthday",
                "author_height",
                "author_passportID");
        var j = 0;
        for (LabWork element : labWorks) {
            int[] lengths = element.getLen();
            for (int i = 0; i < lengths.length; i++) {
                maxLengths.set(i, Math.max(
                        maxLengths.get(i),
                        Math.max(lengths[i], headers.get(i).length()))
                );
            }
            j++;
        }
        printHeader(headers, maxLengths);
        var labWorkList = new ArrayList<>((Collection) LabWorkSet.labWorks);
        printData(labWorkList, maxLengths);
        System.out.println("Total: " + j);
    }

////    @Override
//public boolean checkArgument() {
//    return true;
//}

    @Override
    public String getName() {
        return "Show";
    }

    @Override
    public LabWorkSet getArgument() {
        return (LabWorkSet) this.argument;
    }
}
