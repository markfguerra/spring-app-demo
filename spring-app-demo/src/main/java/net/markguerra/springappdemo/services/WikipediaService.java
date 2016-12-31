package net.markguerra.springappdemo.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Gets information from Wikipedia through the MediaWiki RESTful API
 * 
 * https://www.mediawiki.org/wiki/API:Main_page
 */
@Service
public class WikipediaService {
	private final static String ENDPOINT_URI = "https://en.wikipedia.org/w/api.php";

	private final static String QUERY_URI_TEMPLATE
		= ENDPOINT_URI
		+ "?action=query"	// Query for information
		+ "&titles={title}"	// Article title to query. Value is a URITemplate variable.
		+ "&prop=extracts"	// Query for extracted text of article
		+ "&exintro=true"	// Just the intro text
		+ "&format=json";	// JSON please :D

	private final static String ERROR_TEXT_API = "Error occurred while calling the MediaWiki API :(";
	private final static String ERROR_TEXT_JSON = "JSON Error occurred while using the MediaWiki API :(";

	/**
	 * Gets the intro text of the article, as searched by Title
	 * https://en.wikipedia.org/w/api.php?action=query&titles=pikachu&prop=extracts&exintro=true&format=json
	 * 
	 * @param title
	 * @return
	 */
	public String getArticleIntro(String title) {
		RestTemplate rest = new RestTemplate();
		String output = "";

		ResponseEntity<String> response = null;
		try {
			response = rest.getForEntity(QUERY_URI_TEMPLATE, String.class, title);
		} catch (RestClientException e) {
			// TODO Log this error
			e.printStackTrace();
			return ERROR_TEXT_API;
		}

		System.out.println("Http Status in Wikimedia response: " + response.getStatusCodeValue());

		// Process the response if the HTTP Status is Ok
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			String responseBody = response.getBody();

			try {
				JSONObject json = new JSONObject(responseBody);
				output = getFirstPageText(json);
			} catch (JSONException e) {
				// TODO Log this error
				e.printStackTrace();
				return ERROR_TEXT_JSON;
			}
		} else {
			output = ERROR_TEXT_API;
		}

		return output;
	}

	private String getFirstPageText(JSONObject json) {
		String output;

		// The query may return multiple pages
		JSONObject pages = json
				.getJSONObject("query")
				.getJSONObject("pages");

		// 'pages' is an object, not an array.
		// It can't be accessed by index, so we'll need to access it by key.
		// This is only a demo, so we'll just grab the first key and get its page.
		String firstPageId = pages.names().getString(0);

		// Get the page by key
		JSONObject page = pages.getJSONObject(firstPageId);

		// The text of the page is under the 'extract' key
		output = page.getString("extract");
		return output;
	}
}
