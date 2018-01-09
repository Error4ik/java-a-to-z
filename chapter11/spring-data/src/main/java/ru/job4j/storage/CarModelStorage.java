package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.models.CarModel;

import java.util.List;

/**
 * Car model storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class CarModelStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Get all models from database.
     *
     * @return the list of a models.
     */
    public List<CarModel> getAll() {
        return (List<CarModel>) this.template.find("from CarModel");
    }

    /**
     * Get model by name.
     *
     * @param name name.
     * @return model.
     */
    public CarModel getByName(final String name) {
        return (CarModel) this.template.find("from CarModel where name=?", name).get(0);
    }

    /**
     * Get all models by brand id.
     *
     * @param id id.
     * @return the list of a models.
     */
    public List<CarModel> getModelsByBrandId(final int id) {
        return (List<CarModel>) this.template.find("from CarModel where carBrand.id=?", id);
    }
}
