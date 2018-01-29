package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.domain.Advert;
import ru.job4j.domain.ModelForFillingAdverts;
import ru.job4j.service.AdvertService;

import java.util.List;

/**
 * Advert controller.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Controller
public class AdvertController {

    /**
     * Advert service.
     */
    @Autowired
    private AdvertService advertService;

    /**
     * Mapping web requests /.
     *
     * @return main page.
     */
    @GetMapping(value = "/")
    public ModelAndView getAdverts() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }

    /**
     * Mapping web requests /list.
     *
     * @return the list of ads.
     */
    @RequestMapping("/list")
    public @ResponseBody
    List<Advert> getAll() {
        return this.advertService.getAll();
    }

    /**
     * Mapping web requests /add.
     *
     * @return а page for adding a new advert.
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView getAddView() {
        ModelAndView view = new ModelAndView();
        view.setViewName("add_advert");
        return view;
    }

    /**
     * Mapping web requests /create.
     *
     * @param modelToAdvert model for filling a advert.
     * @param file          file from form.
     * @return redirect to main page.
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addAdvert(ModelForFillingAdverts modelToAdvert, @RequestParam("upFile") MultipartFile file) {
        this.advertService.save(this.advertService.prepareAdvert(modelToAdvert, file));
        return "redirect:/";
    }

    /**
     * Mapping web requests /update.
     *
     * @param id id the ad that will be changed.
     * @return redirect to main page.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String getList(@RequestParam int id) {
        Advert advert = this.advertService.getById(id);
        advert.setSale(!advert.getSale());
        this.advertService.save(advert);
        return "redirect:/";
    }

}
