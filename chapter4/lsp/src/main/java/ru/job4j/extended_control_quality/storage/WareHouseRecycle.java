package ru.job4j.extended_control_quality.storage;

import ru.job4j.extended_control_quality.food.IRecycleFood;
import ru.job4j.simple_control_quality.storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycle ware house.
 */
public abstract class WareHouseRecycle extends Storage {

    /**
     * Contains food.
     */
    private final List<IRecycleFood> recycleFoodList;

    /**
     * Constructor.
     *
     * @param name name.
     */
    public WareHouseRecycle(String name) {
        super(name);
        this.recycleFoodList = new ArrayList<>();
    }

    /**
     * Check condition.
     *
     * @param food food.
     * @return true if the foods can be added.
     */
    public abstract boolean validatesFood(final IRecycleFood food);

    /**
     * Check condition.
     *
     * @param food food.
     * @return true if the foods can be added.
     */
    public boolean addFood(IRecycleFood food) {
        boolean flag = false;
        if (validatesFood(food)) {
            this.recycleFoodList.add(food);
            flag = true;
        }
        return flag;
    }

    /**
     * Method remove food from storage.
     *
     * @param food food.
     */
    public void removeFood(IRecycleFood food) {
        if (this.recycleFoodList.contains(food)) {
            this.recycleFoodList.remove(food);
        }
    }

    /**
     * Get.
     * @return recycle food list.
     */
    public List<IRecycleFood> getRecycleFoodList() {
        return recycleFoodList;
    }

    /**
     * Get.
     *
     * @return size recycle food list.
     */
    public int getAmountOfFoods() {
        return this.recycleFoodList.size();
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
                .append("The number of products: ").append(this.getAmountOfFoods()).append(sep);
        for (IRecycleFood food : recycleFoodList) {
            sb.append(food.toString()).append(sep);
        }
        return sb.toString();
    }
}
