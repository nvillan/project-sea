/**
 * 
 */
package com.seashells.validator;
 
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

/**
 * @author N&Y
 *
 */

@Service
public class RestServiceValidator {

	private static final String SIGNING_SECRET = "PtgVPwxQxkJ4ODA5";
	private static final String SIGNING_KEY = "seashells-92212";

	public boolean verify(HttpHeaders headers) {
		return true;
		// TODO Auto-generated method stub

	}

	public HttpURLConnection sign(String urlParam) throws IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {

		OAuthConsumer consumer = new DefaultOAuthConsumer(SIGNING_KEY, SIGNING_SECRET);
		URL url = new URL(urlParam);

		System.out.println("\nSending 'GET' request to URL : " + url);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		consumer.sign(request);

		return request ;
	}

}
