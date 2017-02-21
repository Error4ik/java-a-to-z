package ru.job4j.extended_control_quality.food;

import ru.job4j.simple_control_quality.food.IFoods;

/**
 * Recycle foods.
 */
public interface IRecycleFood extends IFoods {

    /**
     * Flag recycle foods.
     *
     * @return true if the foods can recycle.
     */
    boolean isRecycle();
}
