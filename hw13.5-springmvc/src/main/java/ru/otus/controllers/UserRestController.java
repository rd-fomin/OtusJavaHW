package ru.otus.controllers;

import org.springframework.web.bind.annotation.*;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;

@RestController
public class UserRestController {

    private final DBServiceUser serviceUser;

    public UserRestController(DBServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable(name = "id") long id) {
        return serviceUser.findById(id).orElse(new User());
    }

    @GetMapping("/api/user")
    public User getUserByName(@RequestParam(name = "name") String name) {
        return serviceUser.findByName(name).orElse(new User());
    }

    @PostMapping("/api/user")
    public long saveUser(@RequestBody User user) {
        return serviceUser.save(user);
    }

}
