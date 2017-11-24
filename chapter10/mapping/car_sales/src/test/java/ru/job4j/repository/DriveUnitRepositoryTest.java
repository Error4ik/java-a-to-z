package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.CarDetails;
import ru.job4j.models.DriveUnit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Drive unit repository test.
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class DriveUnitRepositoryTest {

    private final DriveUnitRepository repository = new DriveUnitRepository();

    @Test
    public void getDriveUnitByName() {
        final CarDetails driveUnit = new DriveUnit();
        driveUnit.setName("four-wheel drive");
        repository.save(driveUnit);
        assertThat(driveUnit.getName(), is(repository.getDriveUnitByName(driveUnit.getName()).getName()));
    }

    @Test
    public void getAllDriveUnits() {
        final CarDetails driveUnit = new DriveUnit();
        driveUnit.setName("front-wheel drive");
        repository.save(driveUnit);
        assertTrue(repository.getAllDriveUnit().contains(driveUnit));
    }

}