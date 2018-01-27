package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.domain.Advert;
import ru.job4j.domain.Car;
import ru.job4j.domain.ModelForFillingAdverts;
import ru.job4j.domain.User;
import ru.job4j.repository.AdvertRepository;
import ru.job4j.util.WritePhotoToDisk;

import java.sql.Timestamp;
import java.util.List;

/**
 * Advert service.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class AdvertService {

    /**
     * Security service.
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Advert storage.
     */
    @Autowired
    private AdvertRepository advertRepository;
    /**
     * Brand service.
     */
    @Autowired
    private CarBrandService brandService;
    /**
     * Model service.
     */
    @Autowired
    private CarModelService modelService;
    /**
     * Car body service.
     */
    @Autowired
    private CarBodyService bodyService;
    /**
     * Engine service.
     */
    @Autowired
    private EngineService engineService;
    /**
     * Transmission service.
     */
    @Autowired
    private TransmissionService transmissionService;
    /**
     * drive unit service.
     */
    @Autowired
    private DriveUnitService driveUnitService;
    /**
     * Car service.
     */
    @Autowired
    private CarService carService;
    /**
     * City service.
     */
    @Autowired
    private CityService cityService;
    /**
     * User service.
     */
    @Autowired
    private UserService userService;
    /**
     * Image service.
     */
    @Autowired
    private ImageService imageService;

    /**
     * Uses storage what would save a advert to database.
     *
     * @param value a advert.
     * @return saved a advert.
     */
    public Advert save(final Advert value) {
        return this.advertRepository.save(value);
    }

    /**
     * Uses storage what would get a advert by id.
     *
     * @param id id.
     * @return a advert.
     */
    public Advert getById(final int id) {
        return this.advertRepository.findById(id).get();
    }

    /**
     * Uses storage what would get the list of a adverts.
     *
     * @return the list of a advert.
     */
    public List<Advert> getAll() {
        return (List<Advert>) this.advertRepository.findAll();
    }

    /**
     * Prepares a advert to save.
     *
     * @param model model contains data to a advert.
     * @param file  file that the client selected.
     * @return a advert.
     */
    public Advert prepareAdvert(final ModelForFillingAdverts model, final MultipartFile file) {
        Advert advert = new Advert();
        advert.setAuthor(this.getUser());
        advert.setTitle(model.getTitle());
        advert.setSale(false);
        advert.setDescription(model.getDescription());
        advert.setPrice(Integer.parseInt(model.getPrice()));
        advert.setYearOfIssue(Integer.parseInt(model.getYearOfIssue()));
        advert.setCreated(new Timestamp(System.currentTimeMillis()));
        advert.setMileage(Integer.parseInt(model.getMileage()));
        advert.setPhoto(this.imageService.save(new WritePhotoToDisk().writePhotoToDisk(file)));
        advert.setCar(this.carService.save(this.fillCar(model)));
        advert.setCity(this.cityService.getByName(model.getCity()));
        return advert;
    }

    /**
     * Test user for a advert.
     * If user not contains in database, he saved to database and return.
     *
     * @return user.
     */
    private User getUser() {
        User user = this.userService.getByEmail(this.securityService.findLoggedUser());
        return user;
    }

    /**
     * Prepare car.
     *
     * @param model model contains car data.
     * @return car.
     */
    private Car fillCar(final ModelForFillingAdverts model) {
        Car car = new Car();
        car.setCarBrand(brandService.getByName(model.getBrand()));
        car.setCarModel(modelService.getByName(model.getModel()));
        car.setCarBody(bodyService.getByName(model.getBody()));
        car.setEngine(engineService.getByName(model.getEngine()));
        car.setTransmission(transmissionService.getByName(model.getTransmission()));
        car.setDriverUnit(driveUnitService.getByName(model.getDriveUnit()));
        return car;
    }
}
