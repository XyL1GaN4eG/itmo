package resources.items;

public class RoomOfBabyBoy implements House {
    @Override
    public String ring() {
        return "звонок между комнатой малыша";
    }

    //еще один вложенный нестатический, обращаемся через объект внешнего
    public class Window {
        public String getName() {
            return "окну";
        }
    }

}
