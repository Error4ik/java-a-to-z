package ru.job4j.simple_control_quality.storage;

import ru.job4j.simple_control_quality.food.IFoods;

/**
 *
 */
public class Trash extends Storage {

    /**
     * Condition in add food.
     */
    private final double shelfLife = 0d;

    /**
     * Constructor.
     *
     * @param name name.
     */
    public Trash(String name) {
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
        if (food.checkShelfLife() == this.shelfLife) {
            flag = true;
        }
        return flag;
    }
}
