package dataClasses;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;

public class LabWorkSet {
    static public LinkedHashSet<LabWork> labWorks = new LinkedHashSet<>();

    public void add(String[] parts) {
//        labWorks.add(new LabWork(
//                parts[0],
//                parts[1],
//                parts[2],
//                parts[3],
//                parts[4],
//                parts[5],
//                parts[6],
//                parts[7],
//        ));
        try {
            labWorks.add(LabWork.builder()
                    .randomID(parts[0])
                    .difficulty(parts[6])
                    .coordinates(parts[2], parts[3])
                    .name(parts[1])
                    .creationDate(ZonedDateTime.parse(parts[4]))
                    .minimalPoint(Float.parseFloat(parts[5]))
                    .author(new Person(parts[7], parts[8], parts[9], parts[10]))
                    .build());
        } catch (IndexOutOfBoundsException e) {
            labWorks.add(LabWork.builder()
                    .randomID(parts[0])
                    .difficulty(parts[6])
                    .coordinates(parts[2], parts[3])
                    .name(parts[1])
                    .creationDate(ZonedDateTime.parse(parts[4]))
                    .minimalPoint(Float.parseFloat(parts[5]))
                    .author(new Person())
                    .build());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

