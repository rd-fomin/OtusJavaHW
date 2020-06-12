package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.core.service.db.DbServiceUser;
import ru.otus.domain.User;

import java.util.List;

@Controller
public class UserController {

    private final DbServiceUser dbServiceUser;

    public UserController(DbServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping({"/", "/user/list"})
    public String userListView(Model model) {
        List<User> users = dbServiceUser.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/user/create")
    public String userCreateView(Model model) {
        model.addAttribute("user", new User());
        return "userCreate";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        dbServiceUser.save(user);
        return new RedirectView("/user/list", true);
    }

}
