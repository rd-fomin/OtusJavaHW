package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.domain.User;
import ru.otus.message.front.FrontendService;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class UserController {

    private final FrontendService frontendService;

    public UserController(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @GetMapping({"/", "/user/list"})
    public String userListView(Model model) {
        AtomicReference<List<User>> users = new AtomicReference<>();
        frontendService.getAllData(users::set);
        model.addAttribute("users", users.get());
        return "userList";
    }

    @GetMapping("/user/create")
    public String userCreateView(Model model) {
        model.addAttribute("user", new User());
        return "userCreate";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        frontendService.save(user, System.out::println);
        return new RedirectView("/user/list", true);
    }

}
