package ru.job4j.simple_control_quality.storage;

import ru.job4j.simple_control_quality.food.IFoods;

import java.util.List;

/**
 * Interface.
 */
public interface IStorage {

    /**
     * Method adds food in storage.
     * @param food food.
     * @return if added return true.
     */
    boolean addFood(final IFoods food);

    /**
     * Method remove food from storage.
     *
     * @param food food.
     */
    void removeFood(final IFoods food);

    /**
     * Method returns list food from storage.
     *
     * @return list.
     */
    List<IFoods> getFoodList();

    /**
     * Get.
     *
     * @return amountOfFoods.
     */
    int getAmountOfFoods();

    /**
     * Check condition.
     *
     * @param food food.
     * @return true if the foods can be added.
     */
    boolean validatesFood(final IFoods food);

    /**
     * Get.
     * @return name storage.
     */
    String getName();
}
