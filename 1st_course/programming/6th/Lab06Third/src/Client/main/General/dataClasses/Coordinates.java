package Client.main.General.dataClasses;

import java.io.Serial;
import java.io.Serializable;

/**
 * Класс General.dataClasses.Coordinates представляет собой координаты точки в двумерном пространстве.
 */
public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = 678L;

    /**
     * Координата X точки.
     */
    private Long x;

    /**
     * Координата Y точки.
     */
    private Double y;

    /**
     * Конструктор создает новый экземпляр координат с заданными значениями координат X и Y.
     *
     * @param x координата X точки.
     * @param y координата Y точки.
     */
    Coordinates(Long x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает строковое представление координат точки в формате "X,Y".
     *
     * @return строковое представление координат точки.
     */
    public String getCoordinates() {
        return x + "," + y;
    }

    /**
     * Возвращает значение координаты X точки.
     *
     * @return значение координаты X точки.
     */
    public Long getX() {
        return x;
    }

    /**
     * Возвращает значение координаты Y точки.
     *
     * @return значение координаты Y точки.
     */
    public Double getY() {
        return y;
    }
}
