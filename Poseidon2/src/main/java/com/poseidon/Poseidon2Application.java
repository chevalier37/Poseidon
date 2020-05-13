package com.poseidon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class Poseidon2Application {

	public static void main(String[] args) {
		SpringApplication.run(Poseidon2Application.class, args);
	}

}
