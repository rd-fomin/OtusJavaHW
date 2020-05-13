package ru.otus.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.core.service.DbServiceUser;
import ru.otus.domain.User;

//@Controller
public class UserController {

    private final DbServiceUser dbServiceUser;

    public UserController(DbServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

//    @GetMapping({"/", "/list"})
    public String userListView() {
        return "userList";
    }

//    @GetMapping("/user/create")
    public String userCreateView(Model model) {
        model.addAttribute("user", new User());
        return "userCreate";
    }

//    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        dbServiceUser.save(user);
        return new RedirectView("/list", true);
    }

}
