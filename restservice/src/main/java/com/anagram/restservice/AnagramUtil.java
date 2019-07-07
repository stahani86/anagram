package com.anagram.restservice;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AnagramUtil {
	
	public static List<String> words = FileUtil.getListOfWordsFromDictionary(FileUtil.filePath);
	
	public static List<String> getAnagrams(String input) {
		
		String sortedInput = getSortedString(input.toLowerCase());
		List<String> anagrams = words.parallelStream().filter(word->{
			String sortedWord = getSortedString(word);
			if(sortedInput.equals(sortedWord)) {
				return true;
			}else return false;
		}).collect(Collectors.toList());
		
		return anagrams;
	}
	
	public static String getSortedString(String input) {
		char[] charArray = input.toLowerCase().toCharArray();
		Arrays.sort(charArray);
		return new String(charArray);
	}
	
	public static int getRandomIndex(int size) {
		return new Random().nextInt(size);
	}
	
	public static String getStringFromList(List<String> words) {
		List<String> list = words.stream().filter(word->word!=null).map(word-> word+" ").collect(Collectors.toList());
		StringBuilder builder = new StringBuilder("");
		for (String string : list) {
			builder.append(string);
		}
		return builder.toString().trim();
	}
}
