public class Coordinates {
    private Long x;
    private Double y;

    Coordinates(Long x, Double y) {
        this.x = x;
        this.y = y;
    }
    public String getCoordinates() {
        return x + "," + y;
    }

    public Long getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}