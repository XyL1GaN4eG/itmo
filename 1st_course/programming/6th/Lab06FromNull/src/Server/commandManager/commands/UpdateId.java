package Server.commandManager.commands;

import General.LabWorkUtility;
import General.dataClasses.TypesOfArguments;
import Server.commandManager.Command;
import General.dataClasses.LabWorkSet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static Server.rsc.ServerGlobalVars.PATH_TO_CSV;

public class UpdateId extends Command {

    public UpdateId(){
        super(false);
    }

    //TODO: переписать чтобы сравнивались не элементы, а айдишники лабворков (аргумент должен быть не интом, а айдишником
    @Override
    public String execute() {
        LabWorkSet.labWorks.removeIf(x -> x.getID() == (int) this.getArgument());
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                PATH_TO_CSV)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = LabWorkUtility.customSplit(line, ',');
                if (parts[0].equals(getArgument())) {
                    LabWorkUtility.addElementToSet(parts);
                }
                break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public String getName() {
        return "update_id";
    }

    @Override
    public TypesOfArguments getType() {
        return TypesOfArguments.LABWORK;
    }


}
