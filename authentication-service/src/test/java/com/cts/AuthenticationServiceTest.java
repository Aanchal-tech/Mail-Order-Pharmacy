package com.cts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.AuthenticationService;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
 class AuthenticationServiceTest {

	@Test
	 void main() {
		AuthenticationService.main(new String[] {});
		assertTrue(true);
	}
}

