package com.poseidon.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poseidon.business.CheckPassword;
import com.poseidon.model.User;
import com.poseidon.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger logger = LogManager.getRootLogger();

	@PostMapping("/user/add")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		String password = user.getPassword();
		boolean isPasswordValid = CheckPassword.isLegalPassword(password);

		if (isPasswordValid) {
			String hashPassword = passwordEncoder.encode((CharSequence) password);
			user.setPassword(hashPassword);
			logger.info("Add user : {}", user);
			return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Password not correct", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/user/findId/{id}")
	public Optional<User> showUpdateForm(@PathVariable("id") Integer id) {
		Optional<User> user = userService.getUserById(id);
		logger.info("Get user : {}", user);
		return user;
	}

	@PutMapping("/user/update/{id}")
	public User updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		user.setId(id);
		logger.info("Update user : {}", user);
		return userService.addUser(user);
	}

	@DeleteMapping("/user/delete/{id}")
	public void deleteBid(@PathVariable("id") Integer id) {
		Optional<User> user = userService.getUserById(id);
		int userId = user.get().getId();
		logger.info("Delete user : {}", user);
		userService.deleteUser(userId);
	}

}
