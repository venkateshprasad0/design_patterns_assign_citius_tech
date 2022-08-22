package com.citius.junit.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.citius.junit.application.EmailValidator;
import com.citius.junit.exception.AcademyException;

public class EmailValidatorTest {
	private static EmailValidator emailValidator;

	public static Collection<Object[]> data() {
		Object[][] data = { { "Sirius_Black", false }, { "Lily_Evans@Hoggy.in", true }, { "Remus_Lups", false },
				{ "NymphieTonks@magic.com", true } };
		return Arrays.asList(data);
	}

	@BeforeAll
	static void Setup() {
		emailValidator = new EmailValidator();
	}

	@Test
	public void validateEmailIdValidEmailId() throws AcademyException {
		assertTrue(emailValidator.validateEmailId("James_Potter@infy.com"));
	}

	@Test
	public void validateEmailIdInvalidEmailId() throws AcademyException {
		assertFalse(emailValidator.validateEmailId("James_Potter@infy.org"));
	}

	@Test
	public void validateEmailIdInvalidEmailIdException() throws AcademyException {
		String email = null;
		assertThrows(AcademyException.class, () -> emailValidator.validateEmailId(email));
	}

	@ParameterizedTest
	@MethodSource("data")
	public void validateEmailIdParamterizedEmailId(String string, boolean expected) throws AcademyException {
		assertTrue(emailValidator.validateEmailId(string) == expected);
	}
}
