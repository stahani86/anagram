package com.anagram.restservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

	public static String filePath = "words_alpha.txt";
	
	public static List<String> getListOfWordsFromDictionary(String filePath) {
		List<String> lines = new ArrayList<String>();
		try(
			InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
			)
		{
			lines = br.lines().collect(Collectors.toList());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lines;
	}
}
