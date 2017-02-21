package ru.job4j.simple_control_quality.manager;

import ru.job4j.simple_control_quality.food.IFoods;
import ru.job4j.simple_control_quality.storage.IStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * ControlQuality.
 */
public class ControlQuality implements IControlQuality {

    /**
     * List storages.
     */
    private final List<IStorage> storageList;

    /**
     * Constructor.
     */
    public ControlQuality() {
        this.storageList = new ArrayList<>();
    }

    /**
     * Method add storages.
     *
     * @param storage storage.
     */
    @Override
    public void addStorage(final IStorage storage) {
        this.storageList.add(storage);
    }

    /**
     * Method remove storages.
     *
     * @param storage storage.
     */
    @Override
    public void removeStorage(final IStorage storage) {
        if (this.storageList.contains(storage)) {
            this.storageList.remove(storage);
        }
    }

    /**
     * Get list storages.
     *
     * @return list.
     */
    @Override
    public List<IStorage> getStorageList() {
        return storageList;
    }

    /**
     * Sorting foods to storages.
     *
     * @param foods food.
     */
    @Override
    public void sortFoods(final IFoods foods) {
        for (IStorage storage : storageList) {
            if (storage.addFood(foods)) {
                break;
            }
        }
    }

    /**
     * Get number storages.
     *
     * @return number.
     */
    @Override
    public int getAmountOfStorages() {
        return this.storageList.size();
    }
}
