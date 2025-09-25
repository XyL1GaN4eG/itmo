package resources.items;

public class HouseOnTree implements House {
    public String getName() {
        return "домиком на дереве";
    }
    @Override
    public String ring() {
        return "звонок между домиком на дереве";
    }
}
