package com.poseidon.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.poseidon.domain.User;
import com.poseidon.repositories.UserRepository;
import com.poseidon.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestUNIT {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void findAllBidListTest() {
		User user = new User("Bob", "1234", "Boby", "admin");
		User user1 = new User("Bob1", "12345", "Boby1", "admin1");
		userRepository.save(user);
		userRepository.save(user1);
		
		List<User> ListUserExpected = new ArrayList<>();
		ListUserExpected.add(user);
		ListUserExpected.add(user1);
		
		List<User> ListUser = userService.findAllUser();
		
		assertEquals(ListUserExpected.size(), ListUser.size());
		
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void addBidTest() {
		User user = new User("Bob", "1234", "Boby", "admin");
		userService.addUser(user);
		
		assertEquals("Bob", userRepository.findById(1).get().getUsername());
		
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void getBidListByIdTest() {
		User user = new User("Bob", "1234", "Boby", "admin");
		userRepository.save(user);
		
		assertEquals("Bob", userService.getUserById(1).get().getUsername());
	}
	
	@Test
	@Sql({"/poseidonTest.sql"}) 
	public void deleteBidListTest() {
		User user = new User("Bob", "1234", "Boby", "admin");
		userRepository.save(user);
		
		userRepository.deleteById(1);
		Optional<User> userOpt = userRepository.findById(1);
		
		Assert.assertFalse(userOpt.isPresent());
	}

}
