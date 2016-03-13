package com.seashells.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

@Path("/sub-mgt")
public class SubscriptionManagement {
	private static final String SIGNING_SECRET = "secret";
	private static final String SIGNING_KEY = "Z8jiGFKbOc";

	@GET
	@Path("/create")
	public Response processNotifyOrder(@QueryParam("url") String urlParam) {

		OAuthConsumer consumer = new DefaultOAuthConsumer(SIGNING_KEY, SIGNING_SECRET);
		URL url;
		try {
			url = new URL(urlParam);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			consumer.sign(request);

			int responseCode = request.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(request.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			return Response.status(responseCode).entity(response.toString()).build();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String result = "<h1>SUB Demo Application</h1>In real world
		// application, a collection of users will be returned !!";
		catch (IOException e) {
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
}