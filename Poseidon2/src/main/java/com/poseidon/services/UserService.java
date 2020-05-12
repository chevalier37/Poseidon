package com.poseidon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.domain.User;
import com.poseidon.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAllUser(){
		return userRepository.findAll();
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

}
