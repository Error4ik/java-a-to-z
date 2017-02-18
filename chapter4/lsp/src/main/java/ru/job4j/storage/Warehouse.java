package ru.job4j.storage;

import ru.job4j.food.IFoods;

/**
 * Simple warehouse.
 */
public class Warehouse extends Storage {

    /**
     * Condition in add food.
     */
    private final double shelfLife = 75d;

    /**
     * Constructor.
     *
     * @param name name.
     */
    public Warehouse(String name) {
        super(name);
    }

    /**
     * Check condition.
     *
     * @param food food.
     * @return true if the foods can be added.
     */
    @Override
    public boolean validatesFood(final IFoods food) {
        boolean flag = false;
        if (food.checkShelfLife() >= shelfLife) {
            flag = true;
        }
        return flag;
    }
}
