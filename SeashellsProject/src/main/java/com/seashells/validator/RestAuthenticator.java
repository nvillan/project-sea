/**
 * 
 */
package com.seashells.validator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.QueryStringSigningStrategy;

/**
 * @author N&Y
 *
 */

@Service
public class RestAuthenticator {

	private static final String SIGNING_SECRET = "PtgVPwxQxkJ4ODA5";
	private static final String SIGNING_KEY = "seashells-92212";

	public boolean verify(HttpHeaders headers, String url) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException{

		Set<String> s =headers.keySet();
		for (Iterator<String> iter = s.iterator(); iter.hasNext(); ) {
		 
			  String headerName = iter.next();
			  System.out.println("" + headerName); 
			}
		
		List<String> header = headers.get("oauth_signature");
		if(header == null){
			return false;
		}
		String oAuthSignature = header.get(0);
		System.out.println("The oAuthSignature in header : " + oAuthSignature);

		OAuthConsumer consumer = new DefaultOAuthConsumer(SIGNING_KEY, SIGNING_SECRET);
		consumer.setSigningStrategy(new QueryStringSigningStrategy());
		String signedUrl = consumer.sign(url);

		System.out.println("The signedUrl : " + signedUrl);
		
		return oAuthSignature.equals(signedUrl);
	}

	public HttpURLConnection sign(String urlParam) throws IOException, OAuthMessageSignerException,
			OAuthExpectationFailedException, OAuthCommunicationException {

		OAuthConsumer consumer = new DefaultOAuthConsumer(SIGNING_KEY, SIGNING_SECRET);
		URL url = new URL(urlParam);

		System.out.println("\nSending 'GET' request to URL : " + url);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		consumer.sign(request);

		return request;
	}

	public String prepareResponse(String reponseReturnString)
			throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		OAuthConsumer consumer = new DefaultOAuthConsumer(SIGNING_KEY, SIGNING_SECRET);
		consumer.setSigningStrategy(new QueryStringSigningStrategy());
		return consumer.sign(reponseReturnString);

	}

}
