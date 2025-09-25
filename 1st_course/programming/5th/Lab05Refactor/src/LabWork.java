import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

public class LabWork {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Person author; //Поле может быть null

    public LabWork() {
    }

//    public LabWork name(String name) {
//        this.name = name;
//        return this;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setMinimalPoint(Float minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    private void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    private void setAuthor(Person author) {
        this.author = author;
    }

    public static LabWorkBuilder builder() {
        return new LabWorkBuilder();
    }

    public static class LabWorkBuilder {

        private LabWork instance = new LabWork();

        public LabWorkBuilder randomID() {
            int id = 0;
            do {
                id = Math.abs((new Random()).nextInt());
            } while (id == 0);
            instance.setId(id);
            return this;
        }

        public LabWorkBuilder randomID(String id) {
            int intID = 0;
            try {
                intID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ID");
            }
            instance.setId(intID);
            return this;
        }

        public LabWork build() {
            return instance;
        }

        public LabWorkBuilder name(String name) {
            instance.setName(name);
            return this;
        }

        public LabWorkBuilder coordinates(String x, String y) {
            instance.setCoordinates(new Coordinates(Long.parseLong(x), Double.parseDouble(y)));
            return this;
        }

        public LabWorkBuilder minimalPoint(float minimalPoint) {
            instance.setMinimalPoint(minimalPoint);
            return this;
        }

        public LabWorkBuilder difficulty(String string) {
            instance.setDifficulty(Difficulty.getByIndexOrName(string));
            return this;
        }

        public LabWorkBuilder creationDate(ZonedDateTime zonedDateTime) {
            instance.setCreationDate(zonedDateTime);
            return this;
        }


        public LabWorkBuilder author(Person author){
            instance.setAuthor(author);
            return this;
        }
    }

    public String[] getMassiv() {
        return new String[]{
                String.valueOf(getID()),
                getName(),
                String.valueOf(coordinates.getX()),
                String.valueOf(coordinates.getY()),
                String.valueOf(getCreationDate()),
                String.valueOf(getMinimalPoint()),
                String.valueOf(getDifficulty()),
                author.getName(),
                String.valueOf(author.getBirthday()),
                String.valueOf(author.getHeight()),
                author.getPassportID()
        };
    }

    public LabWork(
            int id, //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
            String name, //Поле не может быть null, Строка не может быть пустой
            Coordinates coordinates, //Поле не может быть null
            ZonedDateTime creationDate, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
            Float minimalPoint, //Поле не может быть null, Значение поля должно быть больше 0
            Difficulty difficulty, //Поле не может быть null
            String personName,
            Date birthday,
            String height,
            String passportID
    ) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.difficulty = difficulty;

        if (personName != null) {
            try {
                this.author = new Person(personName, String.valueOf(birthday), height, passportID);
            } catch (ParseException e) {
                System.out.println("Дата в неизвестном формате");
            }
        }
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Float getMinimalPoint() {
        return minimalPoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    public String getString() {
        var s =  getID() + "," +
                getName() + "," +
                getCoordinates().getX() + "," +
                getCoordinates().getY() + "," +
                getCreationDate() + "," +
                getMinimalPoint() + "," +
                getDifficulty();

        try {
            if (!author.getName().isEmpty()) {
                s += "," + author.getAll();
                return s;
            }
        } catch (NullPointerException e) {
            return s;
        }

        //до этого s точно код не дойдет потому что в трайкетче это произойдет, а сейчас это просто заглушка
        return s;
    }

    public String[] getString(Boolean blanc) {
        var s =  getID() + "," +
                getName() + "," +
                getCoordinates() + "," +
                getCreationDate() + "," +
                getMinimalPoint() + "," +
                getDifficulty();
        try {
            if (!author.getName().isEmpty()) {
                s += "," + author.getAll();
            }
        } catch (NullPointerException ignore) {
        }

        return LabWorkUtility.customSplit(s, ',');
    }

    public int[] getLen() {
       return new int[]{
         (String.valueOf(getID())).length(),
         (String.valueOf(getName())).length(),
         (String.valueOf(coordinates.getX())).length(),
         (String.valueOf(coordinates.getY())).length(),
         (String.valueOf(getCreationDate())).length(),
         (String.valueOf(getMinimalPoint())).length(),
         (String.valueOf(getDifficulty())).length(),
         (String.valueOf(author.getName())).length(),
         (String.valueOf(author.getBirthday())).length(),
         (String.valueOf(author.getHeight())).length(),
         (String.valueOf(author.getPassportID())).length()
       };
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(int id) {
        this.id = id;
    }
}

