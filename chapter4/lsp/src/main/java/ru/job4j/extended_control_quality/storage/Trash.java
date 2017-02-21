package ru.job4j.extended_control_quality.storage;

import ru.job4j.extended_control_quality.food.IRecycleFood;
import ru.job4j.simple_control_quality.food.IFoods;

/**
 * Trash recycle.
 */
public class Trash extends WareHouseRecycle {

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
    public boolean validatesFood(IRecycleFood food) {
        boolean flag = false;
        if (food.checkShelfLife() == shelfLife && food.isRecycle()) {
            flag = true;
        }

        return flag;
    }

    /**
     * Check condition.
     *
     * @param food food.
     * @return true if the foods can be added.
     */
    @Override
    public boolean validatesFood(IFoods food) {
        return false;
    }

}
