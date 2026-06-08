package com.beautybook.web;

import com.beautybook.model.binding.RegisterBindingModel;
import com.beautybook.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerBindingModel")
    public RegisterBindingModel initRegisterModel() {
        return new RegisterBindingModel();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("registerBindingModel") RegisterBindingModel registerBindingModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {

            userService.register(
                    registerBindingModel.getUsername(),
                    registerBindingModel.getEmail(),
                    registerBindingModel.getPassword()
            );

        } catch (IllegalArgumentException ex) {

            model.addAttribute("registrationError", ex.getMessage());

            return "register";
        }

        return "redirect:/login";
    }
}