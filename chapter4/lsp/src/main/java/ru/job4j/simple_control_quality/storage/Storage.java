package ru.job4j.simple_control_quality.storage;

import ru.job4j.simple_control_quality.food.IFoods;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements part of the interface IStorage.
 */
public abstract class Storage implements IStorage {

    /**
     * Warehouse name.
     */
    private String name;

    /**
     * Contains food.
     */
    private final List<IFoods> foodList;

    /**
     * Constructor.
     *
     * @param name name.
     */
    public Storage(final String name) {
        this.foodList = new ArrayList<>();
        this.name = name;
    }

    /**
     * Method adds food in storage.
     *
     * @param food food.
     */
    @Override
    public boolean addFood(final IFoods food) {
        boolean flag = false;
        if (validatesFood(food)) {
            this.foodList.add(food);
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
    public abstract boolean validatesFood(final IFoods food);

    /**
     * Method remove food from storage.
     *
     * @param food food.
     */
    @Override
    public void removeFood(final IFoods food) {
        if (this.foodList.contains(food)) {
            this.foodList.remove(food);
        }
    }

    /**
     * Method returns list food from storage.
     *
     * @return list.
     */
    @Override
    public List<IFoods> getFoodList() {
        return this.foodList;
    }

    /**
     * Get.
     *
     * @return amountOfFoods.
     */
    @Override
    public int getAmountOfFoods() {
        return this.getFoodList().size();
    }

    /**
     * Get.
     * @return name storage.
     */
    public String getName() {
        return name;
    }

    /**
     * toString.
     *
     * @return description storage.
     */
    @Override
    public String toString() {
        final String sep = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("Name storage: ").append(this.getName()).append(sep)
                .append("The number of products to warehouse: ").append(this.getAmountOfFoods()).append(sep);
        for (IFoods food : foodList) {
            sb.append(food.toString()).append(sep);
        }
        return sb.toString();
    }
}
