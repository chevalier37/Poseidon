package com.poseidon.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.poseidon.domain.User;
import com.poseidon.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

    @PostMapping("/user/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/user/findId/{id}")
    public Optional<User> showUpdateForm(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/user/update/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
    	user.setId(id);
        return userService.addUser(user);
    }

    @DeleteMapping("/user/delete/{id}")
    public void  deleteBid(@PathVariable("id") Integer id) {
    	Optional<User> user = userService.getUserById(id);
    	int userId = user.get().getId();
    	userService.deleteUser(userId);
    }

}
