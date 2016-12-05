package ru.avoronin;

/**
 * ����� ��������� ����� � ������� ���������.
 * @author Alexey Voronin.
 */
public class Point {

    /**
     * ���������� X.
     */
    private double x;

    /**
     * ���������� Y.
     */
    private double y;

    /**
     * ����������� ������������ ����������.
     * @param x ���������� X.
     * @param y ���������� y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * ����� �������� ���������� ���� ����� �������.
     * @param point ����� ������� �������� ����������.
     * @return ���������� ����������.
     */
    public double distanceTo(Point point) {
        double distanceX = this.x - point.x;
        double distanceY = this.y - point.y;
        return Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
    }
}