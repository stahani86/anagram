package com.anagram.restservice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

	public static String filePath = "D:\\Jagadeesh\\parT\\SyedUSA\\wdvg-anagram\\demo\\src\\main\\resources\\words_alpha.txt";
	
	public static List<String> getListOfWordsFromDictionary(String filePath) {
		List<String> lines = new ArrayList<String>();
		try(
			FileReader reader = new FileReader(filePath);
			BufferedReader br = new BufferedReader(reader)
			)
		{
			lines = br.lines().collect(Collectors.toList());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lines;
	}
}
