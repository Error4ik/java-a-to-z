package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.domain.User;
import ru.job4j.service.UserService;

/**
 * Registration controller.
 *
 * @author Alexey Voronin.
 * @since 24.01.2018.
 */
@Controller
public class RegController {

    /**
     * User service.
     */
    @Autowired
    private UserService userService;

    /**
     * User registration page.
     *
     * @return registration page.
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("register");
        return view;
    }

    /**
     * User registration request.
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView regUser(final User user) {
        ModelAndView view = new ModelAndView();
        User u = this.userService.getByEmail(user.getEmail());
        if (u != null) {
            view.setViewName("register");
            view.addObject("error", true);
            return view;
        }
        view.setViewName("index");
        this.userService.regUser(user);
        return view;
    }
}
