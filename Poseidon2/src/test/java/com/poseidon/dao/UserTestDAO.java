package com.poseidon.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.poseidon.domain.User;
import com.poseidon.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestDAO {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	public void userTest() {
		User user = new User("Bob", "124", "Boby", "admin");
		
		// Save
		user = userRepository.save(user);
		Assert.assertNotNull(user.getId());
		assertEquals("Bob", user.getUsername());

		// Update
		user.setRole("user");
		user = userRepository.save(user);
		assertEquals("user", user.getRole());

		// Find
		List<User> listResult = userRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = user.getId();
		userRepository.deleteById(id);
		Optional<User> usersearch = userRepository.findById(id);
		Assert.assertFalse(usersearch.isPresent());
	}

}
