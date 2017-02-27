package ru.job4j.controller;

import ru.job4j.exception.InvalidPointException;
import ru.job4j.model.Field;
import ru.job4j.model.Figure;
import ru.job4j.model.Point;


/**
 * Class determines the winner.
 *
 * @author Alexey Voronin.
 * @since 25.02.2017.
 */
public class WinnerController {

    /**
     * Check winner.
     *
     * @param field where you need to find a winner.
     * @return winner figure or null if winner is not find.
     * @throws InvalidPointException if point invalid throw exception.
     */
    public Figure getWinner(final Field field) throws InvalidPointException {
        try {
            for (int i = 0; i < field.getFieldSize(); i++) {
                if (check(field, new Point(i, 0), new IPointGenerator() {
                    @Override
                    public Point next(Point point) {
                        return new Point(point.getX(), point.getY() + 1);
                    }
                })) {
                    return field.getFigure(new Point(i, 0));
                }
            }

            for (int i = 0; i < field.getFieldSize(); i++) {
                if (check(field, new Point(0, i), new IPointGenerator() {
                    @Override
                    public Point next(Point point) {
                        return new Point(point.getX() + 1, point.getY());
                    }
                })) {
                    return field.getFigure(new Point(0, i));
                }
            }

            if (check(field, new Point(0, 0), new IPointGenerator() {
                @Override
                public Point next(Point point) {
                    return new Point(point.getX() + 1, point.getY() + 1);
                }
            })) {
                return field.getFigure(new Point(0, 0));
            }

            if (check(field, new Point(0, field.getFieldSize() - 1), new IPointGenerator() {
                @Override
                public Point next(Point point) {
                    return new Point(point.getX() + 1, point.getY() - 1);
                }
            })) {
                return field.getFigure(new Point(0, field.getFieldSize() - 1));
            }
        } catch (final InvalidPointException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод получает начальный поинт и поинт генератор, который возвращает следующий поинт.
     * Получает 2 фигуры по этим поинтам и сравнивает их. Если они равны то метод вызывает сам
     * себя и передает в качестве начального поинта следующий поинт который был получен ранее.
     * Например передается поинт 0.0  следующий будет 0.1, если фигуры по этим поинтам равны,
     * то метод вызывает сам себя и передает в качестве начального поинта уже не 0.0, а 0.1
     * при этом следующим поинтом будет 0.2 и так далее по Y и по X осям.
     * <p>
     * The method receives the initial Point and Point generator that returns the next point. Gets 2 figures to
     * these points and compares them. If they are equal then the method invokes itself and transmits an initial
     * Point next Point which had been obtained previously.
     * For example transmitted Point 0.0 will be the next 0.1, if the figures for these points are equal, the method
     * calls itself and passes as the starting Point is not 0.0, and 0.1 with the following points would be 0.2
     * and so further along X and Y axes.
     *
     * @param field          field to find.
     * @param currentPoint   current point.
     * @param pointGenerator Law point of change.
     * @return returns true if the two figures are equal and calls itself passing the next point.
     */
    private boolean check(final Field field, final Point currentPoint, final IPointGenerator pointGenerator) {
        final Figure currentFigure;
        final Figure nextFigure;
        final Point nextPoint = pointGenerator.next(currentPoint);

        try {
            currentFigure = field.getFigure(currentPoint);
            nextFigure = field.getFigure(nextPoint);
        } catch (final InvalidPointException e) {
            return true;
        }
        return currentFigure != null && currentFigure == nextFigure && check(field, nextPoint, pointGenerator);
    }

    /**
     * Law point of change.
     */
    private interface IPointGenerator {
        /**
         * Method takes point and return next point.
         *
         * @param point point.
         * @return next point.
         */
        Point next(final Point point);
    }
}
