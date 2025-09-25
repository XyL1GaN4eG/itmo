package Server.commandManager.commands;

import Server.commandManager.Command;
import General.dataClasses.LabWork;
import General.dataClasses.LabWorkSet;

import java.util.*;

import static General.dataClasses.LabWorkSet.labWorks;

public class Show extends Command {


    public Show() {
        super(true);
    }

    @Override
    public String execute() {
        List<Integer> maxLengths = new ArrayList<>(Collections.nCopies(11, 0));

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

        output += printHeader(headers, maxLengths);
        var labWorkList = new ArrayList<>((Collection) LabWorkSet.labWorks);
        output += printData(labWorkList, maxLengths) + "\n";
        output += ("Total: " + j + "\n");
        return output;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public boolean acceptsCollectionElementOrVariable() {
        return false;
    }

    /**
     * Выводит отформатированную строку.
     *
     * @param values     Массив значений для вывода.
     * @param maxLengths Список максимальных длин столбцов.
     */
    private String printFormattedRow(String[] values, List<Integer> maxLengths) {
        String str = "";
        for (int i = 0; i < values.length; i++) {
            try {
                str += ("| " + values[i] + " ".repeat(Math.max(0, maxLengths.get(i) - values[i].length() + 1)));
            } catch (NullPointerException e) {
                str += ("| " + values[i] + " ".repeat(maxLengths.get(i) - 3));
            }
        }
//        System.out.println("|");
//        output += "|";
//        return output;
        str += "|";
        return str;
    }

    /**
     * Выводит заголовок таблицы.
     *
     * @param headers    Список заголовков.
     * @param maxLengths Список максимальных длин столбцов.
     */
    private String printHeader(List<String> headers, List<Integer> maxLengths) {
        var str = "";
        String[] headerArray = headers.toArray(new String[0]);
        str += "\n" + printFormattedRow(headerArray, maxLengths) + "\n";
        str += (("-".repeat(maxLengths.stream().mapToInt(Integer::intValue).sum() + maxLengths.size() * 3 + 1)) + "\n");
        return str;
    }

    /**
     * Выводит данные таблицы.
     *
     * @param labWorks   Список объектов General.dataClasses.LabWork.
     * @param maxLengths Список максимальных длин столбцов.
     */
    private String printData(List<LabWork> labWorks, List<Integer> maxLengths) {
        var s = "";
        for (LabWork labWork : labWorks) {
            String[] rowData = labWork.getMassiv();
            s += printFormattedRow(rowData, maxLengths) + "\n";
        }
        return s;
    }

    private String output = "";

//    @Override
//    public LabWorkSet getArgument() {
//        return (LabWorkSet) this.argument;
//    }
}
