/**
 * 
 */
package in.mailbomber.FacebookPost;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

/**
 * @author vikas saran
 *
 * 03-Nov-2017
 */
public class TinyUrl {
	
	private static final String GOOGLE_URL_SHORT_API = "https://www.googleapis.com/urlshortener/v1/url";
	private static final String GOOGLE_API_KEY = "AIzaSyDmIcyCXW5OdXfqcJE3o2FHVgmUkGyOh_0";

	public static void main(String[] args) {
		String longUrl = "http://www.timesjobs.com/candidate/mredirect.html";
		String shortUrl = getShortUrl(longUrl);
		System.out.println(shortUrl);
	}
	
	public static String getShortUrl(String longUrl){
		try {
			String apiURL = GOOGLE_URL_SHORT_API+"?longUrl="+longUrl+"&key="+ GOOGLE_API_KEY;
			System.out.println("token api url :" +apiURL);
			String json = "{\"longUrl\": \""+longUrl+"\"}";   

			HttpPost postRequest = new HttpPost(apiURL);
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.setEntity(new StringEntity(json, "UTF-8"));

			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpResponse response = httpClient.execute(postRequest);
			String responseText = EntityUtils.toString(response.getEntity());           

			System.out.println("Tuny URL response"+responseText);
			Gson gson = new Gson();
			@SuppressWarnings("unchecked")
			HashMap<String, String> res = gson.fromJson(responseText, HashMap.class);

			return res.get("id");   	

		    
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException in token url "+longUrl);
			return null;
		} catch (IOException e) {
			System.out.println("IOException in token url "+longUrl);
			return null;
		}
	}

}
