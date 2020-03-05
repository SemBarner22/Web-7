package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.service.UserService;

@Controller
public class UserPage extends Page {
    private UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable("id") String id) {
        long idLong = -1;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            System.out.println("sudo -rm- rf");
        }
        model.addAttribute("user", userService.findById(idLong));
        return "UserPage";
    }
}
