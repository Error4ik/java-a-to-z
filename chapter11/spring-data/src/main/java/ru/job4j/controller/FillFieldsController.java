package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.models.CarDetails;
import ru.job4j.models.CarModel;
import ru.job4j.models.City;
import ru.job4j.service.*;

import java.util.List;

/**
 * Controller to fill the fields in the form creating a new advert.
 *
 * @author Alexey Voronin.
 * @since 01.01.2018.
 */
@Controller
public class FillFieldsController {

    /**
     * Car body service.
     */
    @Autowired
    private CarBodyService bodyService;
    /**
     * Car model service.
     */
    @Autowired
    private CarModelService modelService;
    /**
     * Car brand service.
     */
    @Autowired
    private CarBrandService brandService;
    /**
     * Drive unit service.
     */
    @Autowired
    private DriveUnitService driveUnitService;
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
     * City service.
     */
    @Autowired
    private CityService cityService;


    /**
     * Mapping web requests /carBrand.
     *
     * @return the list of brands.
     */
    @RequestMapping("/carBrand")
    public @ResponseBody
    List<CarDetails> getBrands() {
        return this.brandService.getAll();
    }

    /**
     * Mapping web requests /carModel.
     *
     * @param brand brand name for obtaining models associated with this brand.
     * @return the list of model.
     */
    @RequestMapping("/carModel")
    public @ResponseBody
    List<CarModel> getModels(@RequestParam String brand) {
        return this.modelService.getModelsByBrandId(this.brandService.getByName(brand).getId());
    }

    /**
     * Mapping web requests /engine.
     *
     * @return the list of engines.
     */
    @RequestMapping("/engine")
    public @ResponseBody
    List<CarDetails> getEngines() {
        return this.engineService.getAll();
    }

    /**
     * Mapping web requests /transmission.
     *
     * @return the list of transmissions.
     */
    @RequestMapping("/transmission")
    public @ResponseBody
    List<CarDetails> getTransmissions() {
        return this.transmissionService.getAll();
    }

    /**
     * Mapping web requests /carBody.
     *
     * @return the list of bodies.
     */
    @RequestMapping("/carBody")
    public @ResponseBody
    List<CarDetails> getBodies() {
        return this.bodyService.getAll();
    }

    /**
     * Mapping web requests /driveUnit.
     *
     * @return the list of driver units.
     */
    @RequestMapping("/driveUnit")
    public @ResponseBody
    List<CarDetails> getDriverUnits() {
        return this.driveUnitService.getAll();
    }

    /**
     * Mapping web requests /city.
     *
     * @return the list of city.
     */
    @RequestMapping("/city")
    public @ResponseBody
    List<City> getCities() {
        return this.cityService.getAll();
    }

}
