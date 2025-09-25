import java.util.Date;

public class Person {
    private String name = null; //Поле не может быть null, Строка не может быть пустой
    private Date birthday = null; //Поле не может быть null
    private Integer height; //Значение поля должно быть больше 0
    private String passportID; //Поле может быть null

    public Person(String name, Date birthday, int height, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.passportID = passportID;
    }
    public Person(String str) {
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getHeight() {
        return height;
    }

    public String getPassportID() {
        return passportID;
    }
    public String getAll() {
        return name + "," + birthday + "," + height + "," + passportID;
    }
}
