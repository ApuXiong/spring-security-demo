package com.security.controller;

import com.security.User.User;
import com.security.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("")
    public int create(@RequestBody User user) {
        LOGGER.info("CREATE");
        return userService.saveUser(user);
    }

    @PutMapping("/{id:\\d+}")
    public int update(@RequestBody User user) {
        LOGGER.info("UPDATE");
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {

        LOGGER.info("DELETE");
        System.out.println(id);
    }

    @GetMapping
    public List<User> query(String name) {
        LOGGER.info("QUERY");
        return userService.getUsers(name);
    }

    @GetMapping("/{username:\\d+}")
    public User getInfo(@PathVariable String username) {
        LOGGER.info("GET_INFO");
        return userService.getUserById(username);
    }

    @PostMapping("/mobile")
    public Authentication getUserByMobile(Authentication authentication) {
        LOGGER.info("MOBILE");
        return authentication;
    }
}
