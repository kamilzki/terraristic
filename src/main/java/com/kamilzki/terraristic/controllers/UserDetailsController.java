package com.kamilzki.terraristic.controllers;

import com.kamilzki.terraristic.commands.AnimalCommand;
import com.kamilzki.terraristic.commands.UserCommand;
import com.kamilzki.terraristic.services.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@Slf4j
public class UserDetailsController
{
    UserDetailsServiceImpl userDetailsService;

    public UserDetailsController(UserDetailsServiceImpl userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/login")
    public String login()
    {
        log.debug("Go to /login");
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

//        log.debug(userDetailsService.loadUserByUsername("admin").getPassword());
        log.debug(userDetailsService.loadUserByUsername("czarek").toString());
        log.debug(userDetailsService.loadUserByUsername("mala").toString());


        return "login";
    }

    @GetMapping("/registration")
    public String registration()
    {
        log.debug("get - registration");

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("account")UserCommand userCommand, BindingResult bindingResult)
    {
        log.debug("get - registration");

        if (bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError ->
            {
                log.debug(objectError.toString());
            });
            Map<String, Object> model = bindingResult.getModel();

            return "registration";
        }

        UserCommand savedUserCommand = userDetailsService.saveUserCommand(userCommand);

        return "registration";
    }
}
