import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import

public class Person {
//    Random random = new Random();

//    Long id = Math.abs(random.nextLong());

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int height; //Значение поля должно быть больше 0
    private LocalDate birthday; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле может быть null

    public Person(
            String name,
            Coordinates coordinates,
            int height,
            LocalDate birthday,
            Color hairColor,
            Country nationality,
            Location location) throws Exception {

        Random random = new Random();

        Long id = new Long();

        do {
            id = Math.abs(random.nextLong());
        } while (this.id == 0);

        this.name = name;
        this.coordinates = coordinates;

        LocalDateTime creationDate = LocalDateTime.now();

        if (this.height < 1) {
            throw new Exception("Значение поля должно быть больше 0.");
        }

        this.height = height;
        this.birthday = birthday;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;



    }
}
