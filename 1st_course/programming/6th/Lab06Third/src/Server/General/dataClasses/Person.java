package General.dataClasses;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Person implements Serializable {

//    @Serial
//    private static final long serialVersionUID = 634L;
    private String name;
    private Date birthday;
    private Integer height;
    private String passportID;

    public Person(String name, String birthday, String height, String passportID) throws ParseException {
        if (!height.isEmpty()) {
            this.name = name;
            this.birthday = parseDate(birthday);
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
        return name == null ? "" : name;
    }

    public String getBirthday() {
        var outputDate = "";
        try {
            outputDate = new SimpleDateFormat("yyyy-MM-dd").format(birthday);
        } catch (Exception e) {
            return null;
        }
        return outputDate;
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

    private Date parseDate(String date) throws ParseException {
        String[] dateFormats = {
                "yyyy-MM-dd",
                "EEE MMM dd HH:mm:ss zzz yyyy",
                // добавьте другие форматы по необходимости
        };
        for (String format : dateFormats) {
            try {
                return new SimpleDateFormat(format, Locale.ENGLISH).parse(date);
            } catch (ParseException ignored) {
            }
        }
        throw new ParseException("Unparseable date: \"" + date + "\"", 0);
    }
}
