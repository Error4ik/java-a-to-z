package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Login controller.
 *
 * @author Alexey Voronin.
 * @since 18.01.2018.
 */
@Controller
public class LoginController {

    /**
     * User login mapping.
     *
     * @param error  error.
     * @param logout logout.
     * @return login page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView view = new ModelAndView();
        if (error != null) {
            view.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null) {
            view.addObject("logout", "Logged out successfully.");
        }
        view.setViewName("login");
        return view;
    }
}
