package com.scm1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm1.services.EmailService;

@SpringBootTest
class ScmApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private EmailService service;

	@Test
	void sendEmailTest() {
				service.sendEmail("shwetadas352@gmail.com", "from your ________",
						"Your heart is already hacked. If you want, I can hack your bank account too. But if you don't want that, please send me 10k—it’s an emergency for shopping!");
			}

}
