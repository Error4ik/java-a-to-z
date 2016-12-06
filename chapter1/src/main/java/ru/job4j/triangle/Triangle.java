package ru.job4j.triangle;

import ru.job4j.point.Point;

/**
 * Класс для вычисления площади треугольника.
 */
public class Triangle {

    /**
     * первая точка.
     */
    private Point a;

    /**
     * вторая точка.
     */
    private Point b;

    /**
     * третья точка.
     */
    private Point c;

    /**
     * Конструктор принимает 3 точки для построения треугольника.
     * @param a первая точка.
     * @param b вторая точка.
     * @param c третья точка.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычислят площать треугольника.
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