import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LabWorkUtility {
    // Перегрузка, выводит весь файл

    public static String readFromFile(String filename, Boolean passFirstString) {
        filename = "src/" + filename;
        String returner = "";
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            while ((line = br.readLine()) != null) {
                if (passFirstString) {
                    passFirstString = false;
                    continue; // пропускаем первую строку
                } else {
                    returner += line + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден((");
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returner;
    }

    public static void writeStrToFilename(String str, String filename) {
        String fileName = "src/" + filename; // Путь к вашему файлу
        try (var writer = new PrintWriter(new FileWriter(fileName, true))) { //true нужен чтобы записать
            // в конец файла, а не перезапсиать
            // Записываем текст в файл
            writer.print(str);
        } catch (IOException e) {
            System.err.println("Ошибка записи данных в файл: " + e.getMessage());
        }
    }

    static void addElementToSet(String[] parts) throws Exception {
        var id = Integer.parseInt(parts[0]);
        var name = parts[1];
        long coordinatedX = Long.parseLong(parts[2]);
        double coordinatesY = Double.parseDouble(parts[3]);
        ZonedDateTime date = ZonedDateTime.parse(parts[4]);
        float point = Float.parseFloat(parts[5]);
        Difficulty difficulty = Difficulty.valueOf(parts[6]);
        try {
            int height = Integer.parseInt(parts[9]);
            String personName = parts[7];
            Date personBirthday = null;
            SimpleDateFormat sdf;
            try {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                personBirthday = sdf.parse(parts[8]);
            } catch (ParseException e) {
                var sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                try {
                    personBirthday = sdf1.parse(parts[8]);

                } catch (ParseException ignore) {
                }
            }
            String personID = parts[10];
            Main.linkedHashSet.add(new LabWork(
                    id,
                    name,
                    new Coordinates(coordinatedX, coordinatesY),
                    date,
                    point,
                    difficulty,
                    new Person(personName,
                            personBirthday,
                            height,
                            personID)));
        } catch (NumberFormatException e) {
            Main.linkedHashSet.add(new LabWork(
                    id,
                    name,
                    new Coordinates(coordinatedX, coordinatesY),
                    date,
                    point,
                    difficulty,
                    new Person(null)));
        }
    }


//        Date authorBirthday = null;
//        try {
//            if (parts[8] != null && !parts[8].isEmpty()) { // Проверяем, что строка не пустая
//                SimpleDateFormat sdf = null;
//                sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//                try {
//                    authorBirthday = sdf.parse(parts[8]);
//                } catch (ParseException e) {
//                    sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//                    authorBirthday = sdf.parse(parts[8]);
//                }
//            }
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("60 utility");
//        }
//
//        try { Main.linkedHashSet.add(new LabWork(
//                0,
//                parts[1],
//                new Coordinates()
//        ))
//
////        if (parts[7].trim().equals(""))
//        {
//            try {
//                Main.linkedHashSet.add(new LabWork(
//                        0, //id
//                        parts[1],                   //name
//                        new Coordinates(Long.parseLong(parts[2]), Double.parseDouble(parts[3])), //coordinates
//                        ZonedDateTime.parse(parts[4]),              //time
//                        Float.parseFloat(parts[5]), //minimal point
//                        Difficulty.valueOf(parts[6]), //difficulty
//                        new Person("")));               //passport id
//            } catch (NullPointerException e) {
//                System.out.println("fst");
//                try {
//                    Main.linkedHashSet.add(new LabWork(
//                            0, //id
//                            parts[1],                   //name
//                            new Coordinates(Long.parseLong(parts[2]), Double.parseDouble(parts[3])), //coordinates
//                            ZonedDateTime.parse(parts[4]),              //time
//                            Float.parseFloat(parts[5]), //minimal point
//                            Difficulty.valueOf(parts[6]), //difficulty
//                            new Person(parts[7],        //author name
//                                    authorBirthday,
//                                    Integer.parseInt(parts[9]), //height
//                                    parts[10])));//passport id
//                } catch (Exception ex) {
//                    System.out.println("gay");
//                    ;
//                }
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            } catch (NullPointerException e) {
//                System.out.println("second");
//            }
//        } else {
//            try {
//                Main.linkedHashSet.add(new LabWork(
//                        0, //id
//                        parts[1],                   //name
//                        new Coordinates(Long.parseLong(parts[2]), Double.parseDouble(parts[3])), //coordinates
//                        ZonedDateTime.parse(parts[4]),              //time
//                        Float.parseFloat(parts[5]), //minimal point
//                        Difficulty.valueOf(parts[6]), //difficulty
//                        new Person(parts[7],        //author name
//                                authorBirthday,
//                                Integer.parseInt(parts[9]), //height
//                                parts[10])));//passport id
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }

    public static void switchCaser(String string) throws Exception {
        var parts = customSplit(string, ' ');
        var stringa = "";
        try {
            stringa = String.valueOf(parts[0]);
        } catch (ArrayIndexOutOfBoundsException ignore) {
        }


        if (stringa.equals("help") ||
                stringa.equals("info") ||
                stringa.equals("show") ||
                stringa.equals("add") ||
                stringa.equals("clear") ||
                stringa.equals("save") ||
                stringa.equals("exit") ||
                stringa.equals("history") ||
                stringa.equals("sumOfMinimal")
        ) {
            switch (stringa) {
                case "help" -> LabWorkMethods.help();
                case "info" -> LabWorkMethods.info();
                case "show" -> LabWorkMethods.show();
                case "add" -> LabWorkMethods.add();
                case "clear" -> LabWorkMethods.clear();
                case "save" -> LabWorkMethods.save();
                case "exit" -> LabWorkMethods.exit();
                case "history" -> History.view();
                case "sumOfMinimal" -> LabWorkMethods.sumOfMinimalPoint();

            }
        } else if (
                stringa.equals("sumOfMinimal") ||
                        stringa.equals("executeScript") ||
                        stringa.equals("updateID") ||
                        stringa.equals("removeByID") ||
                        stringa.equals("removeGreater") ||
                        stringa.equals("removeLower") ||
                        stringa.equals("filterByDifficulty") ||
                        stringa.equals("filterLessThanDifficulty")) {
            String second = null;
            var isValidInput = false;

            try {
                second = parts[1];
                isValidInput = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("а где второй аргумент............");
            }
            if (isValidInput) {
                switch (stringa) {
                    case "executeScript" -> {
                        try {
                            LabWorkMethods.executeScript(second);
                        } catch (StackOverflowError e) {
                            System.out.println("стак оверфловнулся ты что наделал((");
                        }
                    }
                    case "updateID", "removeByID", "removeGreater", "removeLower" -> {
                        int secondINT = 0;
                        try {
                            secondINT = Integer.parseInt(second);
                        } catch (NumberFormatException e) {
                            System.out.println("ID должен быть в формате натурального числа.");
                        }
                        switch (stringa) {
                            case "updateID" -> LabWorkMethods.updateID(secondINT);
                            case "removeByID" -> LabWorkMethods.removeByID(secondINT);
                            case "removeGreater" -> LabWorkMethods.removeGreater(secondINT);
                            case "removeLower" -> LabWorkMethods.removeLower(secondINT);
                        }
                    }
                    case "filterByDifficulty", "filterLessThanDifficulty" -> {
                        Difficulty secondDIFF = null;
                        try {
                            secondDIFF = Difficulty.valueOf(second);
                        } catch (NumberFormatException e) {
                            System.out.println("Сложность должна принимать одно из следующих значений: EASY, NORMAL, VERY_HARD, IMPOSSIBLE, INSANE.");
                        }
                        LabWorkMethods.filterByDifficulty(secondDIFF);
                        LabWorkMethods.filterLessThanDifficulty(secondDIFF);
                    }
                }
            }
        } else {
            System.out.println("Операция не найдена. Попробуйте еще раз.");
        }
    }


    //Перегрузка, можно указать количество строк
    public static String readFromFile(String filename, Boolean passFirstString, Integer numberOfStrings) {
        filename = "src/" + filename;
        String returner = "";
        String line;
        var i = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            while ((line = br.readLine()) != null && i <= numberOfStrings) {
                if (passFirstString) {
                    passFirstString = false;
                    continue; // пропускаем первую строку
                } else {
                    i++;
                    returner += line + "\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return returner;
    }

    public static void main(String[] args) {
        var str = "1233483686,LabWork1,0,0.0,2024-02-01T10:15:30Z,,,,,,";
        var list = customSplit(str, ',');
        for (String element : list) {
            System.out.println(element);
        }
    }

    public static String[] customSplit(String str, char delimiter) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        boolean commaSeen = false;
        for (char c : str.toCharArray()) {
            if (c == delimiter) {
                if (commaSeen) {
                    result.add("");
                } else {
                    result.add(sb.toString());
                    sb.setLength(0);
                }
                commaSeen = true;
            } else {
                sb.append(c);
                commaSeen = false;
            }
        }

        if (sb.length() > 0 || commaSeen) {
            result.add(sb.toString());
        }

        return result.toArray(new String[result.size()]);
    }

    public static int countOccurrences(String input, String delimiter) {
        int count = 0;
        int index = 0;
        while ((index = input.indexOf(delimiter, index)) != -1) {
            count++;
            index += delimiter.length();
        }
        return count;
    }
}
