package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.CarDetails;
import ru.job4j.storage.DriveUnitStorage;

import java.util.List;

/**
 * Drive unit service.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class DriveUnitService {

    /**
     * The drive unit storage.
     */
    @Autowired
    private DriveUnitStorage driveUnitStorage;

    /**
     * Get all driver unit from storage.
     *
     * @return the list of driver unit.
     */
    public List<CarDetails> getAll() {
        return this.driveUnitStorage.getAll();
    }

    /**
     * Get drive by name from storage.
     *
     * @param name name.
     * @return drive.
     */
    public CarDetails getByName(String name) {
        return this.driveUnitStorage.getByName(name);
    }
}
