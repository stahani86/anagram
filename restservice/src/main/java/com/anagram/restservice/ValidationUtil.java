package com.anagram.restservice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

	public static boolean validateText(String input) {
		String pattern = "^[a-zA-Z]+([ ]?[a-zA-Z]+)+";
		Pattern concequetivePattern = Pattern.compile(pattern);
		Matcher matcher = concequetivePattern.matcher(input);
		return matcher.matches();
	}
}
