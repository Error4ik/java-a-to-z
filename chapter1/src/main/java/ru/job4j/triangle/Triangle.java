package ru.job4j.triangle;

import ru.job4j.point.Point;

/**
 * ����� ��� ���������� ������� ������������.
 */
public class Triangle {

    /**
     * ������ �����.
     */
    private Point a;

    /**
     * ������ �����.
     */
    private Point b;

    /**
     * ������ �����.
     */
    private Point c;

    /**
     * ����������� ��������� 3 ����� ��� ���������� ������������.
     * @param a ������ �����.
     * @param b ������ �����.
     * @param c ������ �����.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * ����� �������� ������� ������������.
     * @return ���������� �������� ������ ������� ������������.
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