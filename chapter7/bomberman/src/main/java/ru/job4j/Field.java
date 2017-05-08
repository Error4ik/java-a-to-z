package ru.job4j;

/**
 * Field.
 *
 * @author Alexey Voronin.
 * @since 07.05.2017.
 */
public class Field {

    /**
     * Row counts.
     */
    private final int row;

    /**
     * Column counts.
     */
    private final int column;

    /**
     * Cells field.
     */
    private final Cell[][] cells;

    /**
     * Constructor.
     *
     * @param row    row.
     * @param column column.
     */
    public Field(final int row, final int column) {
        this.row = row;
        this.column = column;
        this.cells = new Cell[row][column];
        this.initFields();
    }

    /**
     * Check point to valid coordinate.
     *
     * @param point point.
     * @return true if point valid.
     */
    private boolean checkPoint(final Point point) {
        boolean flag = false;
        if (point.getX() >= 0 && point.getY() >= 0 && point.getX() < this.row && point.getY() < this.column) {
            flag = true;
        }
        return flag;
    }

    /**
     * Fills the field.
     */
    private void initFields() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.cells[i][j] = new Cell(new Point(i, j));
            }
        }
    }

    /**
     * Get cell from field.
     *
     * @param point point.
     * @return cell or null if invalid point.
     */
    public Cell getCell(final Point point) {
        Cell cell = null;
        if (this.checkPoint(point)) {
            cell = this.cells[point.getX()][point.getY()];
        }
        return cell;
    }

}
