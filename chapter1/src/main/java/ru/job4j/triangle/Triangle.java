package ru.job4j.triangle;

import ru.job4j.point.Point;

/**
 *  ласс дл€ вычислени€ площади треугольника.
 */
public class Triangle {

    /**
     * перва€ точка.
     */
    private Point a;

    /**
     * втора€ точка.
     */
    private Point b;

    /**
     * треть€ точка.
     */
    private Point c;

    /**
     *  онструктор принимает 3 точки дл€ построени€ треугольника.
     * @param a перва€ точка.
     * @param b втора€ точка.
     * @param c треть€ точка.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * ћетод вычисл€т площать треугольника.
     * @return возвращает значение равное площади треугольника.
     */
    public double area() {
        final double ab = a.distanceTo(b);
        final double bc = b.distanceTo(c);
        final double ac = a.distanceTo(c);
        if (ab + ac <= bc || ab + bc <= ac || ac + bc <= ab) {
            return 0d;
        }
        final double p = (ab + bc + ac) / 2;
        return Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));
    }
}