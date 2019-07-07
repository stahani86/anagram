package com.anagram.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

public class RestInvoker {
	
		
	public static void main(String[] args) throws Exception {
		
		ClientConfig config = new ClientConfig();
		config.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, "true");
		JerseyClient client = JerseyClientBuilder.createClient(config);
		//Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(UriBuilder.fromUri("http://localhost:3000/anagram").build());
		MediaType[] mediaTypes = new MediaType[] { MediaType.APPLICATION_JSON_TYPE };
		AnagramRequest anagramRequest = new AnagramRequest();
		int serviceCallCount = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean shouldContinue = true;
		do {
			System.out.println("Enter Input Phrase : ");
			String intialInputPhrase = br.readLine();
			String inputPhrase = intialInputPhrase;
			do {
				System.out.println(" Input : "+inputPhrase);
				anagramRequest.setPhrase(inputPhrase);
				Entity<AnagramRequest> entity = Entity.entity(anagramRequest, MediaType.APPLICATION_JSON_TYPE);
				Response response = target.request(mediaTypes).build(HttpMethod.GET, entity).invoke();
				serviceCallCount++;
				if(response.getStatus() == 200) {
					AnagramResponse anagramResponse = response.readEntity(AnagramResponse.class);
					String anagram = anagramResponse.getAnagram();
					System.out.println(" Output : "+anagram);
					if(anagram.equalsIgnoreCase(intialInputPhrase)) {
						break;
					}else {
						inputPhrase = anagram;
					}
				}else {
					StatusType statusInfo = response.getStatusInfo();
					String reason = statusInfo.getReasonPhrase();
					int statusCode = statusInfo.getStatusCode();
					throw new Exception("Error in service call : status code="+statusCode+" , ErrorReason : "+reason);
				}
			}while(serviceCallCount<10);
			System.out.println(serviceCallCount);
			System.out.println("Do You want to check another anagram?Y/N");
			String flag = br.readLine();
			if("Y".equalsIgnoreCase(flag)) {
				continue;
			}else break;
		}while(shouldContinue);
		br.close();
	}
}