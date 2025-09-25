import java.time.ZonedDateTime;
import java.util.Random;

public class LabWork {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Person author; //Поле может быть null

    public LabWork(
            int id, //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
            String name, //Поле не может быть null, Строка не может быть пустой
            Coordinates coordinates, //Поле не может быть null
            ZonedDateTime creationDate, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
            Float minimalPoint, //Поле не может быть null, Значение поля должно быть больше 0
            Difficulty difficulty, //Поле не может быть null
            Person author
    ) {
        Random random = new Random();
        do {
            this.id = Math.abs(random.nextInt());
        } while (this.id == 0);

        if (name.isEmpty()) {
            this.author = new Person(null);
        } else {
            if ( coordinates == null || creationDate == null || minimalPoint == null || difficulty == null ) {
                throw new IllegalArgumentException("Поле не может быть null");
            } else {
                this.name = name;
                this.coordinates = coordinates;
                this.creationDate = creationDate;
                this.minimalPoint = minimalPoint;
                this.difficulty = difficulty;
            }
        }


//        if (author == null) {
//            this.author = null;
//        } else {
//            this.author = new Person(author.getName(), author.getBirthday(), author.getHeight(), author.getPassportID());
//        }
    }

//    public LabWork(
//            int id, //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
//            String name, //Поле не может быть null, Строка не может быть пустой
//            Coordinates coordinates, //Поле не может быть null
//            ZonedDateTime creationDate, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
//            Float minimalPoint, //Поле не может быть null, Значение поля должно быть больше 0
//            Difficulty difficulty //Поле не может быть null
//    ) throws Exception {
//        Random random = new Random();
//        do {
//            this.id = Math.abs(random.nextInt());
//        } while (this.id == 0);
//
//        if (name == null || name.isEmpty() || coordinates == null || creationDate == null || minimalPoint == null || difficulty == null) {
//            throw new IllegalArgumentException("Поле не может быть null");
//        } else {
//            this.name = null;
//            this.coordinates = null;
//            this.creationDate = null;
//            this.minimalPoint = null;
//            this.difficulty = null;
//        }
//    }

    public int getID() {
        return id;
    }

    public Float getMinimalPoint() {
        return minimalPoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getAll() {
        // author == null ? ",,,,," :
        try {
            return  id + "," +
                    name + "," +
                    coordinates.getCoordinates() + "," +
                    creationDate + "," +
                    minimalPoint + "," +
                    difficulty + "," +
                    author.getAll();
        } catch (NullPointerException e) {
            return  id + "," +
                    name + "," +
                    coordinates.getCoordinates() + "," +
                    creationDate + "," +
                    minimalPoint + "," +
                    difficulty
                    + "nulllll ," + "," + "," + ","
                    ;
        }
    }

    public String getString() {
        var str = id + "\t" +
        name + "\t" +
        coordinates.getCoordinates() + "\t\t\t" +
        creationDate + "\t" +
        minimalPoint + "\t\t\t\t" +
        difficulty + "\t";

        return str;
    }
}

