package com.seashells.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seashells.exception.AccountCreationException;
import com.seashells.manager.SubscriptionManager;
import com.seashells.model.Result;
import com.seashells.validator.RestAuthenticator;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

/**
 * The Rest Service controller class is responsible for handling all the rest
 * calls from App Direct.
 *
 * @author Natalie Villanueva
 * @version 1.0
 */

@RestController
public class RestServiceController {

	final static Logger logger = Logger.getLogger(RestServiceController.class);

	@Autowired
	private RestAuthenticator restAuthenticator;

	@Autowired
	private SubscriptionManager subscriptionManager;

	/**
	 * Process notify order method handles the subscription order event. It
	 * validates that the rest call comes from App Direct, signs the url using
	 * OAuth and sends it back to App Direct. If valid we receive the event data
	 * and create the new customer account.
	 * 
	 * @param headers
	 *            the headers
	 * @param urlParam
	 *            the url param
	 * @return the response entity
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<String> processNotifyOrder(@RequestHeader HttpHeaders headers,
			@RequestParam(value = "url", required = true) String urlParam) {

		try {
			// 1. Validate that rest call is coming from App Direct
			if (!getRestAuthenticator().verify(headers, urlParam)) {
				return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			}

			// 2. If call is from App Direct, sign it and send it back
			HttpURLConnection response = getRestAuthenticator().sign(urlParam);

			if (logger.isDebugEnabled()) {
				logger.debug("Response Code : " + response.getResponseCode());
			}
			// 3. Create account
			int accoutNumber = getSubscriptionManager().createAccount(response);

			if (logger.isInfoEnabled()) {
				logger.info("Account created is :\n" + accoutNumber);
			}
			// 4. Send the response
			Result result = new Result();
			result.setSuccess(true);
			result.setMessage("Account creation successful " + accoutNumber);
			result.setAccountIdentifier(String.valueOf(accoutNumber));
			ResponseEntity<String> re = createResponse(result);
			return re;

		} catch (JAXBException e) {
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
		} catch (AccountCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 5. Exception flow Send the response
		Result result = new Result();
		result.setSuccess(false);
		result.setMessage("An error occurred .");
		ResponseEntity<String> re = createResponse(result);
		return re;

	}

	private ResponseEntity<String> createResponse(Result result) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_XML);
		String accountIdentifier = result.getAccountIdentifier();
		StringBuffer bf = new StringBuffer();

		bf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><result><success>");
		bf.append(String.valueOf(result.isSuccess()));
		bf.append("</success><message>");
		bf.append(result.getMessage());
		bf.append("</message>");
		if (StringUtils.isEmpty(accountIdentifier)) {
			bf.append("</result>");
		} else {
			bf.append("<accountIdentifier>");
			bf.append(accountIdentifier);
			bf.append("</accountIdentifier></result>");
		}
		return new ResponseEntity<String>(bf.toString(), httpHeaders, HttpStatus.OK);
	}

	/**
	 * Process notify cancel method handles the subscription cancel event. It
	 * validates that the rest call comes from App Direct, signs the url using
	 * OAuth and sends it back to App Direct. Then, we receive the event data
	 * and cancel the account.
	 * 
	 * 
	 * @param headers
	 *            the headers
	 * @param urlParam
	 *            the url param
	 * @return the response entity
	 * @throws AccountCreationException
	 */

	@RequestMapping(value = "/cancel", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<String> processNotifyCancel(@RequestHeader HttpHeaders headers,
			@RequestParam(value = "url", required = true) String urlParam) throws AccountCreationException {

		try {

			// 1. Validate that rest call is coming from App Direct
			if (!getRestAuthenticator().verify(headers, urlParam)) {
				return null;
			}

			// 2. Its call from App Direct, lets sign it and send it back
			HttpURLConnection response = getRestAuthenticator().sign(urlParam);

			if (logger.isDebugEnabled()) {
				logger.debug("Response Code : " + response.getResponseCode());
			}
			// 3. Cancel account
			int accoutNumber = getSubscriptionManager().cancelAccount(response);

			if (logger.isInfoEnabled()) {
				logger.info("Account " + accoutNumber + " is cancelled.");
			}
			// 4. Send the response
			Result result = new Result();
			result.setSuccess(true);
			result.setAccountIdentifier(String.valueOf(accoutNumber));
			result.setMessage("Account cancelled successful.");
			ResponseEntity<String> re = createResponse(result);
			return re;

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
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

	}

	/**
	 * @return the restValidator
	 */
	public RestAuthenticator getRestAuthenticator() {
		return restAuthenticator;
	}

	/**
	 * @param restValidator
	 *            the restValidator to set
	 */
	public void setRestAuthenticator(RestAuthenticator restValidator) {
		this.restAuthenticator = restValidator;
	}

	/**
	 * @return the subscriptionManager
	 */
	public SubscriptionManager getSubscriptionManager() {
		return subscriptionManager;
	}

	/**
	 * @param subscriptionManager
	 *            the subscriptionManager to set
	 */
	public void setSubscriptionManager(SubscriptionManager subscriptionManager) {
		this.subscriptionManager = subscriptionManager;
	}
}