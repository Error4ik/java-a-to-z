package ru.job4j.extended_control_quality.manager;

import ru.job4j.extended_control_quality.food.IRecycleFood;
import ru.job4j.extended_control_quality.storage.WareHouseRecycle;
import ru.job4j.simple_control_quality.manager.ControlQuality;

import java.util.ArrayList;
import java.util.List;

/**
 * Extended control quality.
 */
public class ExtendedControlQuality extends ControlQuality {

    /**
     * List storages.
     */
    private final List<WareHouseRecycle> recycleStorageList;

    /**
     * Constructor.
     */
    public ExtendedControlQuality() {
        this.recycleStorageList = new ArrayList<>();
    }

    /**
     * Method add storages.
     *
     * @param storage storage.
     */
    public void addStorages(WareHouseRecycle storage) {
        this.recycleStorageList.add(storage);
    }

    /**
     * Method remove storages.
     *
     * @param storage storage.
     */
    public void removeStorage(WareHouseRecycle storage) {
        if (this.recycleStorageList.contains(storage)) {
            this.recycleStorageList.remove(storage);
        }
    }

    /**
     * Get list storages.
     *
     * @return list.
     */
    public List<WareHouseRecycle> getRecycleStorageList() {
        return this.recycleStorageList;
    }

    /**
     * Sorting foods to storages.
     *
     * @param foods food.
     */
    public void sortRecycleFoods(IRecycleFood foods) {
        for (WareHouseRecycle wareHouseRecycle : recycleStorageList) {
            if (wareHouseRecycle.addFood(foods)) {
                break;
            }
        }
    }

    /**
     * Get number storages.
     *
     * @return number.
     */
    public int getAmountOfStorages() {
        return this.recycleStorageList.size();
    }
}
