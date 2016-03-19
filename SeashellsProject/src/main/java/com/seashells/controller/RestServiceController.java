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
import com.seashells.validator.RestServiceValidator;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

@RestController
public class RestServiceController {

	@Autowired
	private RestServiceValidator restValidator;

	@Autowired
	private SubscriptionManager subscriptionManager;

	@RequestMapping(value = "/create", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<String> processNotifyOrder(@RequestHeader HttpHeaders headers,
			@RequestParam(value = "url", required = true) String urlParam) {

		// String reponseString = "<?xml version=\"1.0\" encoding=\"UTF-8\"
		// standalone=\"yes\"?><event
		// xmlns:atom=\"http://www.w3.org/2005/Atom\"><type>SUBSCRIPTION_ORDER</type><marketplace><baseUrl>https://acme.appdirect.com</baseUrl><partner>ACME</partner></marketplace><flag>STATELESS</flag><creator><email>test-email+creator@appdirect.com</email><firstName>DummyCreatorFirst</firstName><language>fr</language><lastName>DummyCreatorLast</lastName><openId>https://www.appdirect.com/openid/id/ec5d8eda-5cec-444d-9e30-125b6e4b67e2</openId><uuid>ec5d8eda-5cec-444d-9e30-125b6e4b67e2</uuid></creator><payload><company><country>CA</country><email>company-email@example.com</email><name>Example
		// Company
		// Name</name><phoneNumber>415-555-1212</phoneNumber><uuid>d15bb36e-5fb5-11e0-8c3c-00262d2cda03</uuid><website>http://www.example.com</website></company><configuration><entry><key>domain</key><value>mydomain</value></entry></configuration><order><editionCode>BASIC</editionCode><pricingDuration>MONTHLY</pricingDuration><item><quantity>10</quantity><unit>USER</unit></item><item><quantity>15</quantity><unit>MEGABYTE</unit></item></order></payload><returnUrl>https://www.appdirect.com/finishprocure?token=dummyOrder</returnUrl></event>";

		// 1. Validate that rest call is coming from App Direct
		if (!getRestValidator().verify(headers)) {
			return null;
		}

		try {

			// 2. Its call from App Direct, lets sign it and send it back
			HttpURLConnection response = getRestValidator().sign(urlParam);
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

		// 1. Validate that rest call is coming from App Direct
		if (!getRestValidator().verify(headers)) {
			return null;
		}

		try {

			// 2. Its call from App Direct, lets sign it and send it back
			HttpURLConnection response = getRestValidator().sign(urlParam);
			System.out.println("Response Code : " + response.getResponseCode());

			// 3. Cancel account
			int accoutNumber = getSubscriptionManager().cancelAccount(response);
			// int accoutNumber = 40;
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
	public RestServiceValidator getRestValidator() {
		return restValidator;
	}

	/**
	 * @param restValidator
	 *            the restValidator to set
	 */
	public void setRestValidator(RestServiceValidator restValidator) {
		this.restValidator = restValidator;
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