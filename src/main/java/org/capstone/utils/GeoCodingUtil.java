package org.capstone.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class GeoCodingUtil {

	private static String API_KEY = "AIzaSyDx5iAD-pgWNp3mvAE0aY3b6U4IU0Gr-dY";

	public static String reverseGeoCoding(String lng, String lat) {
		URL url = null;
		try {
			url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&sensor=true"
					+ "&key=" + API_KEY);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection urlConnection = null;

		try {
			urlConnection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String formattedAddress = "";
		try {
			InputStream in = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String result, line = reader.readLine();
			result = line;
			while ((line = reader.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
			JSONObject rsp = new JSONObject(result);
			JSONArray matches = rsp.getJSONArray("results");
			
			JSONObject data = (JSONObject) matches.get(0);
			formattedAddress = (String) data.get("formatted_address");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(formattedAddress);
		return formattedAddress;

	}
}
