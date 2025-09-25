import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Date birthday; //Поле не может быть null
    private Integer height; //Значение поля должно быть больше 0
    private String passportID; //Поле может быть null

    public Person(String name, String birthday, String height, String passportID) throws ParseException {

        if (height != "") {

            var sdf = new SimpleDateFormat();
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date personBirthday = null;
            try {
                personBirthday = sdf.parse(birthday);
            } catch (IllegalArgumentException | NullPointerException | ParseException e) {
                var sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                try {
                    personBirthday = sdf1.parse(birthday);
                } catch (ParseException ignore) {
                    System.out.println("ну пиздец");
                }
            }

            this.name = name;
            this.birthday = personBirthday;
            this.height = Integer.parseInt(height);
            this.passportID = passportID;
        }
    }

    public Person(String name, Date birthday, Integer height, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.passportID = passportID;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getHeight() {
        return String.valueOf(height);
    }

    public String getPassportID() {
        return passportID;
    }
    public String getAll() {
        return name + "," + birthday + "," + height + "," + passportID;
    }

}
