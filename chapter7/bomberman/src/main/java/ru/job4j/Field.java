package ru.job4j;

/**
 * Field.
 *
 * @author Alexey Voronin.
 * @since 09.05.2017.
 */
public class Field {

    /**
     * Number of lines.
     */
    private final int row;

    /**
     * Number of columns.
     */
    private final int column;

    /**
     * Cell.
     */
    private final Cell[][] cells;

    /**
     * Constructor.
     * @param row number of lines.
     * @param column number of columns.
     */
    public Field(final int row, final int column) {
        this.row = row;
        this.column = column;
        this.cells = new Cell[row][column];
        this.fillField();
    }

    /**
     * Get.
     * @param point point for which you need to return the cell.
     * @return cell if point valid, or null.
     */
    public Cell getCell(final Point point) {
       Cell cell = null;
        if (checkPoint(point)) {
           cell = this.cells[point.getX()][point.getY()];
        }
        return cell;
    }

    /**
     * Check valid point.
     * @param point point to check
     * @return true if point valid.
     */
    public boolean checkPoint(final Point point) {
        boolean flag = false;
        if (point.getX() >= 0 && point.getY() >= 0 && point.getX() < this.row && point.getY() < this.column) {
            flag = true;
        }
        return flag;
    }

    /**
     * Get.
     * @return row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Get.
     * @return column.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Fill field.
     */
    private void fillField() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.cells[i][j] = new Cell(new Point(i, j));
                if (i % 2 != 0 && j %  2 != 0) {
                    this.getCell(new Point(i, j)).getLock().lock();
                }
            }
        }
    }
}
