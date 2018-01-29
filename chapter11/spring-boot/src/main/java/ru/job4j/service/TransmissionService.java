package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Transmission;
import ru.job4j.repository.TransmissionRepository;

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
    private TransmissionRepository transmissionRepository;

    /**
     * Get all transmissions from storage.
     *
     * @return the list of transmissions.
     */
    public List<Transmission> getAll() {
        return (List<Transmission>) this.transmissionRepository.findAll();
    }

    /**
     * Get transmission by name from storage.
     *
     * @param name name.
     * @return transmission.
     */
    public Transmission getByName(String name) {
        return this.transmissionRepository.findByName(name);
    }
}
