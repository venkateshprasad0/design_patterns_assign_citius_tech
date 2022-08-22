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

import com.citius.junit.application.PasswordValidator;
import com.citius.junit.exception.AcademyException;

public class PasswordValidatorTest {
	
	private static PasswordValidator passwordValidator;
	
	public static Collection<Object[]> data() {
		Object[][] data = { { "Qwerty", false }, { "Qwerty1234", true }, { "QwertyAsdfZxcvVcxzFdsaRewq", false },
				{"Zxcvb54321", true } };
		return Arrays.asList(data);
	}
	
	@BeforeAll
	static void setUp() {
		passwordValidator = new PasswordValidator();
	}
	
	@Test
	public void validatePasswordValidPassword() throws AcademyException {
		assertTrue(passwordValidator.validatePassword("Asdf1234"));
	}

	@Test
	public void validatePasswordInvalidPassword() throws AcademyException {
		assertFalse(passwordValidator.validatePassword("asdf"));
	}

	@Test
	public void validatePasswordInvalidPasswordException() throws AcademyException {
		String passString = null;
		assertThrows(AcademyException.class, () -> passwordValidator.validatePassword(passString));
	}

	@ParameterizedTest
	@MethodSource("data")
	public void validatePasswordParameterizedPassword(String str, boolean expected) throws AcademyException {
		assertTrue(passwordValidator.validatePassword(str) == expected);
	}
}
