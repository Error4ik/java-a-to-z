package ru.job4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Class frog moves from the start point to the finish of the minimum number of moves.
 *
 * @author Alexey Voronin.
 * @since 06.03.2017.
 */
public class FrogWay {

    /**
     * Start point.
     */
    private final Point start;

    /**
     * End point.
     */
    private final Point finish;

    /**
     * Array obstacles points.
     */
    private final Point[] tree;

    /**
     * Minimum quantity steps to find finish point..
     */
    private int minimumStep;

    /**
     * Path to show by console.
     */
    private String path = "";

    /**
     * Constructor.
     *
     * @param start start point.
     * @param finish   finish point.
     * @param tree  array to obstacles.
     */
    public FrogWay(final Point start, final Point finish, final Point[] tree) {
        this.start = start;
        this.finish = finish;
        this.tree = tree;
    }

    /**
     * Method displays the full path from start to finish.
     * @return path from start to finish.
     */
    public String getWay() {
        this.recursMove(start, 0, this.path);
        return String.format("Minimum move: %s%s%s", this.getMinimumStep(), System.getProperty("line.separator"), this.path);
    }

    /**
     * Method recursively calls itself passing through all the points and searches short way.
     * @param start start point.
     * @param count the number of moves made.
     * @param tempPath distance traveled.
     */
    private void recursMove(final Point start, final int count, final String tempPath) {
        int maxDeepRecurs = 20;
        if (start.equals(this.finish)) {
            if (this.minimumStep == 0 || this.minimumStep > count) {
                this.minimumStep = count;
                this.path = tempPath;
            }
        } else if (count < maxDeepRecurs && this.minimumStep == 0 || this.minimumStep > count) {
            List<Point> pointList = this.getWay(start);
            for (Point point : pointList) {
                this.recursMove(point, count + 1, tempPath + "(" + point.getX() + "," + point.getY() + ")");
            }
        }
    }

    /**
     * The method adds to the list the coordinates in which you can go from the current point.
     *
     * @param point current point.
     * @return list coordinates.
     */
    private List<Point> getWay(final Point point) {
        List<Point> wayList = new ArrayList<>();
        final int row = 16;
        Point step = new Point((point.getX() + 1) % row, point.getY() - 2);
        if (this.canMove(step)) {
            wayList.add(step);
        }
        step = new Point((point.getX() + 1) % row, point.getY() + 2);
        if (this.canMove(step)) {
            wayList.add(step);
        }
        step = new Point((point.getX() + 2) % row, point.getY() - 1);
        if (this.canMove(step)) {
            wayList.add(step);
        }
        step = new Point((point.getX() + 2) % row, point.getY() + 1);
        if (this.canMove(step)) {
            wayList.add(step);
        }
        step = new Point((point.getX() + 3) % row, point.getY());
        if (this.canMove(step)) {
            wayList.add(step);
        }
        return wayList;
    }

    /**
     * Check coordinate to move.
     *
     * @param point point.
     * @return return true if coordinate valid.
     */
    private boolean canMove(final Point point) {
        boolean flag = false;
        if (this.validPoint(point) && !this.isObstacle(point)) {
            flag = true;
        }
        return flag;
    }

    /**
     * Check that coordinate did not go beyond the field.
     *
     * @param point point.
     * @return true if coordinate valid.
     */
    private boolean validPoint(final Point point) {
        boolean flag = false;
        if (point.getX() < 16 && point.getX() >= 0 && point.getY() < 16 && point.getY() >= 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * Check coordinate to obstacle.
     *
     * @param point point.
     * @return true if point do not move to obstacle.
     */
    private boolean isObstacle(final Point point) {
        boolean flag = false;
        for (Point obstacle : tree) {
            if (obstacle.equals(point)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Get.
     *
     * @return minimum step count to find.
     */
    private int getMinimumStep() {
        return minimumStep;
    }
}
