package Server.commandManager.commands;

import General.LabWorkUtility;
import Server.commandManager.Command;
import General.dataClasses.LabWork;
import General.dataClasses.LabWorkSet;

import java.io.File;

import static Server.GlobalVars.PATH_TO_CSV;


//TODO: переписать с учетом что должен быть автосейв + использовать джава стримы
public class Save extends Command {

    public Save(){
        super(false);
    }

    @Override
    public String execute() {
        var str = "";


        var filename = PATH_TO_CSV;

        var file = new File(filename);
        file.delete();
        LabWorkUtility.writeStrToFilename("id,name,coordinates_x,coordinates_y,creationDate,minimalPoint,difficulty,author_name,author_birthday,author_height,author_passportID\n", filename);

        for (LabWork element : LabWorkSet.labWorks) {
            str = str + element.getString() + "\n";
        }
        LabWorkUtility.writeStrToFilename(str, filename);

        return "Коллекция сохранена в файл " + filename;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public boolean acceptsCollectionElementOrVariable() {
        return false;
    }
}
