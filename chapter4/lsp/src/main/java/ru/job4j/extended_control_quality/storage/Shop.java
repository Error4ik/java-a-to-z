package ru.job4j.extended_control_quality.storage;

import ru.job4j.extended_control_quality.food.IRecycleFood;
import ru.job4j.simple_control_quality.food.IFoods;

/**
 * Shop.
 */
public class Shop extends WareHouseRecycle {

    /**
     * Condition in add food.
     */
    private final double maxLife = 75d;

    /**
     * Condition in add food.
     */
    private final double minLife = 1d;

    /**
     * Condition in add food.
     */
    private final double discountLife = 25d;

    /**
     * Constructor.
     *
     * @param name name.
     */
    public Shop(String name) {
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
        double shelfLife = food.checkShelfLife();
        if (shelfLife < maxLife && shelfLife > minLife) {
            if (shelfLife <= discountLife) {
                food.setDiscount(25);
            }
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
