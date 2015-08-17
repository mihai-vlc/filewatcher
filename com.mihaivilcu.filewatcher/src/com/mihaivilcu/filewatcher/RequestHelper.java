package com.mihaivilcu.filewatcher;

import java.io.*;
import java.net.*;

public class RequestHelper {

	/**
	 * Makes a get request to the provided url.
	 * 
	 * @param urlToRead
	 *            The url to be accessed.
	 * @return The received content or an empty string.
	 */
	public static String getHTML(String urlToRead) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		StringBuilder result = new StringBuilder();
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		} catch (IOException e) {
			return "";
			// e.printStackTrace();
		} catch (Exception e) {
			return "";
			// e.printStackTrace();
		}
		return result.toString();
	}
}
