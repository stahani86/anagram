package com.anagram.restservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnagramRestController {
	
	@RequestMapping( method = RequestMethod.GET,  path = "/anagram", produces = {"application/json"}, consumes = {"application/json"})
	public @ResponseBody ResponseEntity<AnagramResponse> getAnagramResponse(@RequestBody AnagramRequest request) {
		
		AnagramResponse anagramResponse = new AnagramResponse();
		if(request == null) {
			anagramResponse.setErrorMessage("The request can not be empty/null");
			anagramResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
			ResponseEntity<AnagramResponse> response = new ResponseEntity<AnagramResponse>(anagramResponse, HttpStatus.NO_CONTENT);
			return response;
		}
		else if(StringUtils.isEmpty(request.getPhrase()) ) {
			anagramResponse.setErrorMessage("Phrase can not be empty/null");
			anagramResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			ResponseEntity<AnagramResponse> response = new ResponseEntity<AnagramResponse>(anagramResponse, HttpStatus.BAD_REQUEST);
			return response;
		}
		else if(request.getPhrase().length()>=1024 ) {
			anagramResponse.setErrorMessage("Phrase must be < 1024 characters in length");
			anagramResponse.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			ResponseEntity<AnagramResponse> response = new ResponseEntity<AnagramResponse>(anagramResponse, HttpStatus.NOT_ACCEPTABLE);
			return response;
		}
		else if(!ValidationUtil.validateText(request.getPhrase().trim()) ) {
			anagramResponse.setErrorMessage("Phrase should only contain alphabetical characters (no punctuation or special characters) and spaces");
			anagramResponse.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			ResponseEntity<AnagramResponse> response = new ResponseEntity<AnagramResponse>(anagramResponse, HttpStatus.NOT_ACCEPTABLE);
			return response;
		}else {
			String phrase = request.getPhrase();
			List<String> words = Arrays.asList(phrase.split(" "));
			ArrayList<String> outList = new ArrayList<String>();
			for (String word : words) {
				List<String> anagrams = AnagramUtil.getAnagrams(word);
				if(anagrams.size()>0) {
					int randomIndex = AnagramUtil.getRandomIndex(anagrams.size());
					String anagram = anagrams.get(randomIndex);
					outList.add(anagram);
				}else {
					outList.add(word);
				}
			}
			String output = AnagramUtil.getStringFromList(outList);
			anagramResponse.setAnagram(output);
			ResponseEntity<AnagramResponse> response = new ResponseEntity<AnagramResponse>(anagramResponse, HttpStatus.OK);
			return response;
		}	
	}
	
	@RequestMapping( method = RequestMethod.POST,  path = "/anagram", produces = {"application/json"}, consumes = {"application/json"})
	public @ResponseBody ResponseEntity<AnagramResponse> getAnagramResponseUsingPost(@RequestBody AnagramRequest request) {
		return this.getAnagramResponse(request);
	}
}
