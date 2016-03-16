package com.seashells.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.seashells.manager.UserManager;
import com.seashells.model.Creator;
import com.seashells.model.SubscriptionOrderEvent;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

@RestController   
public class RestConroller {
	private static final String SIGNING_SECRET = "PtgVPwxQxkJ4ODA5";
	private static final String SIGNING_KEY = "seashells-92212";
	
	@Autowired
	private UserManager userManager;
 
	@RequestMapping(value = "/create" , method = RequestMethod.GET)
	public Response processNotifyOrder(@RequestParam(value="url", required=true) String urlParam) {

		OAuthConsumer consumer = new DefaultOAuthConsumer(SIGNING_KEY, SIGNING_SECRET);
		URL url;
		try {
			url = new URL(urlParam);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			consumer.sign(request);

			int responseCode = request.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			RestTemplate restTemplate = new RestTemplate();

			SubscriptionOrderEvent result = restTemplate.getForObject(response.toString(), SubscriptionOrderEvent.class);
			
			Creator c = result.getCreator();
			getUserManager().addUser(c);
			

			System.out.println(result);
			in.close();
			String reponseString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><result><success>true</success><message>Account creation successful for Fake Co. by Alice</message><accountIdentifier>fakeco123</accountIdentifier></result>";
	 
			
			return Response.status(responseCode).entity(reponseString).build();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}