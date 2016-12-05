package ru.avoronin;

/**
 *  ласс описывает точку в системе координат.
 * @author Alexey Voronin.
 */
public class Point {

    /**
     *  оордината X.
     */
    private double x;

    /**
     *  оордината Y.
     */
    private double y;

    /**
     *  онструктор инииализиует координаты.
     * @param x координата X.
     * @param y координата y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * ћетод вычисл€т рассто€ние меду двум€ точками.
     * @param point точка котора€ содержит координаты.
     * @return возвращает рассто€ние.
     */
    public double distanceTo(Point point) {
        double distanceX = this.x - point.x;
        double distanceY = this.y - point.y;
        return Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
    }
}