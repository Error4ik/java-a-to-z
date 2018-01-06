package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.CarDetails;
import ru.job4j.storage.TransmissionStorage;

import java.util.List;

/**
 * Transmission service.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class TransmissionService {

    /**
     * The transmissions storage.
     */
    @Autowired
    private TransmissionStorage transmissionStorage;

    /**
     * Get all transmissions from storage.
     *
     * @return the list of transmissions.
     */
    public List<CarDetails> getAll() {
        return this.transmissionStorage.getAll();
    }

    /**
     * Get transmission by name from storage.
     *
     * @param name name.
     * @return transmission.
     */
    public CarDetails getByName(String name) {
        return this.transmissionStorage.getByName(name);
    }
}
