package com.travelapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringBootTravelApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(4, 2 + 2);
	}

	@Test
	void contextLoads2() {
		assertNotEquals(5, 2+2);
	}

}
