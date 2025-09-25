package General.dataClasses;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;

public class LabWorkSet implements Serializable {
    @Serial
    private static final long serialVersionUID = 502L;
    static public LinkedHashSet<LabWork> labWorks = new LinkedHashSet<>();

//    public static void myAdd(LabWork parts) {
//        try {
//            labWorks.add(LabWork.builder()
//                    .randomID(parts[0])
//                    .difficulty(parts[6])
//                    .coordinates(parts[2], parts[3])
//                    .name(parts[1])
//                    .creationDate(ZonedDateTime.parse(parts[4]))
//                    .minimalPoint(Float.parseFloat(parts[5]))
//                    .author(new Person(parts[7], parts[8], parts[9], parts[10]))
//                    .build());
//        } catch (IndexOutOfBoundsException e) {
//            labWorks.add(LabWork.builder()
//                    .randomID(parts[0])
//                    .difficulty(parts[6])
//                    .coordinates(parts[2], parts[3])
//                    .name(parts[1])
//                    .creationDate(ZonedDateTime.parse(parts[4]))
//                    .minimalPoint(Float.parseFloat(parts[5]))
//                    .author(new Person())
//                    .build());
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

