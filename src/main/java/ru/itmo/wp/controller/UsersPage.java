package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.DisableCredentials;
import ru.itmo.wp.form.NoticeCredentials;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("disableForm", new DisableCredentials());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String banPost(@Valid @ModelAttribute("disableForm") DisableCredentials disableForm,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "UsersPage";
        }
        if (getUser(httpSession) == null) {
            putMessage(httpSession, "You are not even signed in to ban");
            return "redirect:/";
        }
        userService.changeDisability(disableForm.getId(), !disableForm.isDisability());
        putMessage(httpSession, "Congrats, you have been caught banning!");

        return "redirect:/";
    }

}
