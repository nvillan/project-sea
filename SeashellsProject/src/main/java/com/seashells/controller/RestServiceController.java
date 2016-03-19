package com.seashells.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seashells.manager.SubscriptionManager;
import com.seashells.model.Result;
import com.seashells.validator.RestAuthenticator;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

@RestController
public class RestServiceController {

	@Autowired
	private RestAuthenticator restAuthenticator;

	@Autowired
	private SubscriptionManager subscriptionManager;

	@RequestMapping(value = "/create", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<String> processNotifyOrder(@RequestHeader HttpHeaders headers,
			@RequestParam(value = "url", required = true) String urlParam) {

		// 1. Validate that rest call is coming from App Direct
		try {
			if (!getRestAuthenticator().verify(headers, urlParam)) {
				return null;
			}

			// 2. Its call from App Direct, lets sign it and send it back
			HttpURLConnection response = getRestAuthenticator().sign(urlParam);
			System.out.println("Response Code : " + response.getResponseCode());

			// 3. Create account
			int accoutNumber = getSubscriptionManager().createAccount(response);
			System.out.println("printing account num :\n" + accoutNumber);

			// 4. Send the response
			String reponseReturnStringOriginal = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><result><success>true</success><message>Account creation successful for Fake Co. by Alice</message><accountIdentifier>"
					+ String.valueOf(accoutNumber) + "</accountIdentifier></result>";

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_XML);

			Result result = new Result();
			result.setSuccess(true);
			result.setMessage("Account creation successful for nat.");
			result.setAccountIdentifier(String.valueOf(accoutNumber));

			ResponseEntity<String> re = new ResponseEntity<String>(reponseReturnStringOriginal, httpHeaders,
					HttpStatus.OK);
			System.out.println("printing response entity :\n" + re.toString());

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
		}

		return null;

	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<String> processNotifyCancel(@RequestHeader HttpHeaders headers,
			@RequestParam(value = "url", required = true) String urlParam) {

		try {

			// 1. Validate that rest call is coming from App Direct
			if (!getRestAuthenticator().verify(headers, urlParam)) {
				return null;
			}

			// 2. Its call from App Direct, lets sign it and send it back
			HttpURLConnection response = getRestAuthenticator().sign(urlParam);
			System.out.println("Response Code : " + response.getResponseCode());

			// 3. Cancel account
			int accoutNumber = getSubscriptionManager().cancelAccount(response);

			// 4. Send the response
			String reponseReturnStringOriginal = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><result><success>true</success><message>Account creation successful for Fake Co. by Alice</message><accountIdentifier>"
					+ String.valueOf(accoutNumber) + "</accountIdentifier></result>";

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_XML);

			Result result = new Result();
			result.setSuccess(true);
			result.setMessage("Account cancelled successful for nat.");

			ResponseEntity<String> re = new ResponseEntity<String>(reponseReturnStringOriginal, httpHeaders,
					HttpStatus.OK);
			System.out.println("printing response entity :\n" + re.toString());

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

		return null;

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