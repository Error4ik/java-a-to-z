package ru.job4j.controllers;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.models.Advert;
import ru.job4j.models.Car;
import ru.job4j.models.Photo;
import ru.job4j.models.User;
import ru.job4j.repository.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Create new advert.
 *
 * @author Alexey Voronin.
 * @since 08.11.2017.
 */
@Log4j
public class CreateController extends HttpServlet {

    /**
     * Map save form field.
     */
    private final ConcurrentMap<String, String> formField = new ConcurrentHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        try {
            List<FileItem> items = fileUpload.parseRequest(req);
            this.fillMap(items);
            Car car = fillCar();

            Photo photo = new Photo();
            photo.setId(Integer.parseInt(formField.get("photoId")));

            new AdvertRepository().save(fillAdvert(car, user, photo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        resp.sendRedirect("index.html");
    }

    private Advert fillAdvert(@NonNull final Car car, @NonNull final User user, @NonNull final Photo photo) {
        Advert advert = new Advert();
        advert.setTitle(formField.get("title"));
        advert.setDescription(formField.get("description"));
        advert.setSale(false);
        advert.setAuthor(user);
        advert.setCreated(new Timestamp(System.currentTimeMillis()));
        advert.setCity(new CityRepository().getCityByName(formField.get("city")));
        advert.setPrice(Long.parseLong(formField.get("price")));
        advert.setPhoto(photo);
        advert.setCar(car);
        advert.setYearOfIssue(Integer.parseInt(formField.get("yearOfIssue")));
        advert.setMileage(Integer.parseInt(formField.get("mileage")));
        return advert;
    }

    private Car fillCar() {
        Car car = new Car();
        car.setCarBrand(new BrandRepository().getBrandByName(this.formField.get("brand")));
        car.setCarModel(new ModelRepository().getModelByName(this.formField.get("model")));
        car.setCarBody(new CarBodyRepository().getBodyByName(this.formField.get("body")));
        car.setEngine(new EngineRepository().getEngineByName(this.formField.get("engine")));
        car.setTransmission(new TransmissionRepository().getTransmissionByName(this.formField.get("transmission")));
        car.setDriverUnit(new DriveUnitRepository().getDriveUnitByName(this.formField.get("drive_unit")));
        car.setId(new CarRepository().save(car));
        return car;
    }

    private void fillMap(@NonNull final List<FileItem> items) throws Exception {
        this.formField.clear();
        for (FileItem item : items) {
            if (item.isFormField()) {
                formField.put(item.getFieldName(), item.getString("UTF-8"));
            } else {
                File dir = new File("C:/upload/test/");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir.getAbsolutePath() + "/" + item.getName());
                item.write(file);
                Photo photo = new Photo();
                photo.setName(file.getName());
                photo.setImageUrl(file.getCanonicalPath());
                int id = new PhotoRepository().save(photo);
                formField.put("photoId", String.valueOf(id));
            }
        }
    }
}
