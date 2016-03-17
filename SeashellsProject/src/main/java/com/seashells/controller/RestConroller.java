package com.seashells.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jboss.resteasy.util.HttpResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seashells.manager.UserManager;
import com.seashells.model.Creator;
import com.seashells.model.Result;
import com.seashells.model.SubscriptionOrderEvent;
import com.seashells.model.SubscriptionPayload;

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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public Result processNotifyOrder(@RequestParam(value = "url", required = true) String urlParam) {

		String reponseString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><event xmlns:atom=\"http://www.w3.org/2005/Atom\"><type>SUBSCRIPTION_ORDER</type><marketplace><baseUrl>https://acme.appdirect.com</baseUrl><partner>ACME</partner></marketplace><flag>STATELESS</flag><creator><email>test-email+creator@appdirect.com</email><firstName>DummyCreatorFirst</firstName><language>fr</language><lastName>DummyCreatorLast</lastName><openId>https://www.appdirect.com/openid/id/ec5d8eda-5cec-444d-9e30-125b6e4b67e2</openId><uuid>ec5d8eda-5cec-444d-9e30-125b6e4b67e2</uuid></creator><payload><company><country>CA</country><email>company-email@example.com</email><name>Example Company Name</name><phoneNumber>415-555-1212</phoneNumber><uuid>d15bb36e-5fb5-11e0-8c3c-00262d2cda03</uuid><website>http://www.example.com</website></company><configuration><entry><key>domain</key><value>mydomain</value></entry></configuration><order><editionCode>BASIC</editionCode><pricingDuration>MONTHLY</pricingDuration><item><quantity>10</quantity><unit>USER</unit></item><item><quantity>15</quantity><unit>MEGABYTE</unit></item></order></payload><returnUrl>https://www.appdirect.com/finishprocure?token=dummyOrder</returnUrl></event>";
		Response resp = null;
		OAuthConsumer consumer = new DefaultOAuthConsumer(SIGNING_KEY, SIGNING_SECRET);
		URL url;
		try {
			url = new URL(urlParam);

			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			consumer.sign(request);

			int responseCode = request.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(request.getInputStream()));
			// String inputLine;
			// StringBuffer response = new StringBuffer();
			//
			// while ((inputLine = in.readLine()) != null) {
			// response.append(inputLine);
			// }

			JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(SubscriptionOrderEvent.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			// StringReader sr = new StringReader(response);
			SubscriptionOrderEvent subscriptionOrderEvent = (SubscriptionOrderEvent) jaxbUnmarshaller
					.unmarshal(request.getInputStream());
			System.out.println("printing dummy order :\n" + subscriptionOrderEvent);

			Creator c = subscriptionOrderEvent.getCreator();
			SubscriptionPayload sub = subscriptionOrderEvent.getPayload();
			int acctNum = getUserManager().addUser(c, sub);

			System.out.println("printing account num :\n" + acctNum);
			// in.close();
			String reponseReturnString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><result><success>true</success><message>Account creation successful for Fake Co. by Alice</message><accountIdentifier>seashellApp123456</accountIdentifier></result>";

			System.out.println("printing response string  :\n" + reponseReturnString);
			Result rs = new Result();
			rs.setSuccess(true);
			rs.setMessage("Account creation successful ");
			rs.setAccountIdentifier("seashellApp" + acctNum);

			return rs;
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

	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager
	 *            the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}