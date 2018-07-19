package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.domain.User;
import com.kamilzki.terraristic.services.SecurityService;
import com.kamilzki.terraristic.services.UserService;
import com.kamilzki.terraristic.validator.UserValidator;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j
public class UserController {

    private static final String REGISTRATION = "/registration";

    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator)
    {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping(value = REGISTRATION)
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return REGISTRATION;
    }

    @PostMapping(value = REGISTRATION)
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        log.debug("post - " + REGISTRATION);

        if (bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError ->
            {
                log.debug(objectError.toString());
            });
            Map<String, Object> model = bindingResult.getModel();

            return REGISTRATION;
        }

        userService.saveUser(user);

        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        return "login";
    }
}

