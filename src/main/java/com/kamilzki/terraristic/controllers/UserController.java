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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@Slf4j
public class UserController
{
    private final static String USER_ROLE = "USER";

    UserDetailsServiceImpl userDetailsService;

    public UserController(UserDetailsServiceImpl userDetailsService)
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
    public String registration(Model model)
    {
        log.debug("get - registration");

        UserCommand userCommand = new UserCommand();
        model.addAttribute("user", userCommand);

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user")UserCommand userCommand, BindingResult bindingResult)
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

        userCommand.setRoles(new String[]{USER_ROLE});
        UserCommand savedUserCommand = userDetailsService.saveUserCommand(userCommand);

        log.debug("Create user: " + savedUserCommand.getId() + ", " + savedUserCommand.getUsername()
                + ", " + savedUserCommand.getPassword() + ", " + savedUserCommand.getRoles());
        return "redirect:/login";
    }
}
