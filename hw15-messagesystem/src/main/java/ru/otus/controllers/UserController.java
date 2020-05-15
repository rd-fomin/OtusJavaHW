package ru.otus.controllers;

import org.springframework.ui.Model;

//@Controller
public class UserController {

//    @GetMapping()
    public String userListView(Model model) {
        return "userList.html";
    }

//    @GetMapping()
    public String userCreateView() {
        return "userCreate.html";
    }

    /*@PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        dbServiceUser.save(user);
        return new RedirectView("/list", true);
    }*/

}
