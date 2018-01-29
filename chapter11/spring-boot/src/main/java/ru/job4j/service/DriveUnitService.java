package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.DriveUnit;
import ru.job4j.repository.DriveUnitRepository;

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
    private DriveUnitRepository driveUnitRepository;

    /**
     * Get all driver unit from storage.
     *
     * @return the list of driver unit.
     */
    public List<DriveUnit> getAll() {
        return (List<DriveUnit>) this.driveUnitRepository.findAll();
    }

    /**
     * Get drive by name from storage.
     *
     * @param name name.
     * @return drive.
     */
    public DriveUnit getByName(String name) {
        return this.driveUnitRepository.findByName(name);
    }
}
