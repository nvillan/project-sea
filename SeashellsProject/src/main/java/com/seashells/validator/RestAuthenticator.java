/**
 * 
 */
package com.seashells.validator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.seashells.controller.RestServiceController;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.QueryStringSigningStrategy;

/**
 * The Class RestAuthenticator.
 *
 * @author Natalie Villanueva
 */

@Service
public class RestAuthenticator {

	final static Logger logger = Logger.getLogger(RestServiceController.class);

	/** The Constant AUTHORIZATION_HEADER. */
	private static final String AUTHORIZATION_HEADER = "authorization";

	/** The Constant SIGNING_SECRET. */
	// TODO: Should be part of a keystore for security
	private static final String SIGNING_SECRET = "3Lk4JaRNn18RZMKD";

	/** The Constant SIGNING_KEY. */
	private static final String SIGNING_KEY = "sea-app-96156";

	/**
	 * Verify.
	 *
	 * @param headers
	 *            the headers
	 * @param url
	 *            the url
	 * @return true, if successful
	 * @throws OAuthMessageSignerException
	 *             the o auth message signer exception
	 * @throws OAuthExpectationFailedException
	 *             the o auth expectation failed exception
	 * @throws OAuthCommunicationException
	 *             the o auth communication exception
	 */
	public boolean verify(HttpHeaders headers, String url)
			throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {

		List<String> authHeader = headers.get(AUTHORIZATION_HEADER);
		if (authHeader == null) {
			return false;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("The authorization header content: " + authHeader);

			for (Iterator<String> iter = authHeader.iterator(); iter.hasNext();) {
				String headerName = iter.next();
				logger.debug(headerName + " \n");
			}
		}

		// ("oauth_signature");
		String oAuthParameters = authHeader.get(0);
		if (logger.isDebugEnabled()) {
			logger.debug("The oAuthParameters in header : " + oAuthParameters);
		}
		
		//TODO: Fix validation for the signature
		OAuthConsumer consumer = new DefaultOAuthConsumer(SIGNING_KEY, SIGNING_SECRET);
		consumer.setSigningStrategy(new QueryStringSigningStrategy());

		String signedUrl = consumer.sign(url);

		if (logger.isDebugEnabled()) {
			logger.debug("The signedUrl : " + signedUrl);
		}
		return true;
	}

	/**
	 * Sign.
	 *
	 * @param urlParam
	 *            the url param
	 * @return the http url connection
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws OAuthMessageSignerException
	 *             the o auth message signer exception
	 * @throws OAuthExpectationFailedException
	 *             the o auth expectation failed exception
	 * @throws OAuthCommunicationException
	 *             the o auth communication exception
	 */
	public HttpURLConnection sign(String urlParam) throws IOException, OAuthMessageSignerException,
			OAuthExpectationFailedException, OAuthCommunicationException {

		OAuthConsumer consumer = new DefaultOAuthConsumer(SIGNING_KEY, SIGNING_SECRET);
		URL url = new URL(urlParam);
		if (logger.isDebugEnabled()) {
			logger.debug("\nSending 'GET' request to URL : " + url);
		}
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		consumer.sign(request);

		return request;
	}

	/**
	 * Prepare response.
	 *
	 * @param reponseReturnString
	 *            the reponse return string
	 * @return the string
	 * @throws OAuthMessageSignerException
	 *             the o auth message signer exception
	 * @throws OAuthExpectationFailedException
	 *             the o auth expectation failed exception
	 * @throws OAuthCommunicationException
	 *             the o auth communication exception
	 */
	public String prepareResponse(String reponseReturnString)
			throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		OAuthConsumer consumer = new DefaultOAuthConsumer(SIGNING_KEY, SIGNING_SECRET);
		consumer.setSigningStrategy(new QueryStringSigningStrategy());
		return consumer.sign(reponseReturnString);

	}

}
