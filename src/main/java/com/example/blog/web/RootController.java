package com.example.blog.web;

import com.example.blog.model.User;
import com.example.blog.services.UserService;
import com.example.blog.to.UserTo;
import com.example.blog.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class RootController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    @Autowired
    public RootController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "registration")
    public String registration(Model model) {
        model.addAttribute("userTo", new UserTo());
        return "registration";
    }

    @PostMapping(value = "registration")
    public String registration(@Valid @ModelAttribute("userTo") UserTo userTo, BindingResult bindingResult, Model model) {

        bindingResult.getAllErrors().forEach(System.out::println);

        if (!userTo.isNew()) {
            bindingResult.rejectValue("username", userTo + " must be new (id=null)");
        }
        if (!userTo.getPassword().equals(userTo.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        try {
            log.info("create {}", userTo);
//            checkNew(userTo);
            User user = userService.create(UserUtil.createNewFromTo(userTo));
            System.out.println(user + " " + user.getPassword());

            userService.autologin(user.getLogin(), user.getPassword());

            return "redirect:welcome";

        } catch (Exception e) {
            bindingResult.rejectValue("username", "Duplicate.userForm.username");
        }
        return "registration";
    }

    @GetMapping(value = "login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping(value = "welcome")
    public String welcome(Model model) {
        return "welcome";
    }

    @GetMapping(value = "messages")
    public String messages(Model model) {
        return "messages";
    }

    @GetMapping(value = "profile")
    public String profile(Model model) {
        return "profile";
    }

//    @GetMapping(value = "game")
//    public String game(Model model) {
//        return "game";
//    }

    @GetMapping
    public String root(Model model) {
        return "redirect:welcome";
    }


}
