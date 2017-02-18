package ru.job4j.manager;

import ru.job4j.food.IFoods;
import ru.job4j.storage.IStorage;

import java.util.List;

/**
 * Interface Control Quality.
 */
public interface IControlQuality {

    /**
     * Method add storages.
     *
     * @param storage storage.
     */
    void addStorage(final IStorage storage);

    /**
     * Method remove storages.
     *
     * @param storage storage.
     */
    void removeStorage(final IStorage storage);

    /**
     * Get list storages.
     *
     * @return list.
     */
    List<IStorage> getStorageList();

    /**
     * Sorting foods to storages.
     *
     * @param foods food.
     */
    void sortFoods(final IFoods foods);

    /**
     * Get number storages.
     * @return number.
     */
    int getAmountOfStorages();
}
