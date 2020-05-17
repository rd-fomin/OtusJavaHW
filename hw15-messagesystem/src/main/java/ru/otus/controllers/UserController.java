package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping({"/", "/list"})
    public String userListView(Model model) {
//        List<User> users = new ArrayList<>();
//        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/user/create")
    public String userCreateView() {
        return "userCreate";
    }

}
