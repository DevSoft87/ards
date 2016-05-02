package com.devsoft;

import com.devsoft.contacts.logic.ContactLogic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactsApplicationTests {


	@Test
	public void checkValidationRegexNull() {
		boolean actual = ContactLogic.isValidRegex(null, null);
		assertEquals(false, actual);
	}
	@Test
	public void checkValidationRegex1() {
		boolean actual = ContactLogic.isValidRegex("hello", null);
		assertEquals(false, actual);
	}
	@Test
	public void checkValidationRegex2() {
		boolean actual = ContactLogic.isValidRegex("hello", "^A.*$");
		assertEquals(false, actual);
	}
	@Test
	public void checkValidationRegex3() {
		boolean actual = ContactLogic.isValidRegex("Andrey", "^A.*$");
		assertEquals(true, actual);
	}
	@Test
	public void checkValidationRegex4() {
		boolean actual = ContactLogic.isValidRegex("Andrey", "^.*[aei].*$");
		assertEquals(true, actual);
	}
	@Test
	public void checkValidationRegex5() {
		boolean actual = ContactLogic.isValidRegex("Help", "^.*[a].*$");
		assertEquals(false, actual);
	}
}
