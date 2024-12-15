package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiSearch {
    /// THE PROJECT WILL NOT WORK IF API KEY DOES NOT EXIST!!
    /// CREATE YOUR OWN API KEY!
    private static final String API_KEY = System.getenv("GOOGLE_API_KEY");
    private static final String CX = System.getenv("GOOGLE_CX");

    public static String performSearch(String query) {
        String urlString = "https://www.googleapis.com/customsearch/v1?q=" + query + "&key=" + API_KEY + "&cx=" + CX;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return parseResults(response.toString());
        } catch (Exception e) {
            return "there is no internet connection.";
        }
    }

    private static String parseResults(String jsonResponse) {
        StringBuilder results = new StringBuilder();
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray items = jsonObject.getJSONArray("items");
            if (!items.isEmpty()) {
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    String title = item.getString("title");
                    String link = item.getString("link");
                    String snippet = item.getString("snippet");
                    results.append("Title: ").append(title).append("\n");
                    results.append("Link: ").append(link).append("\n");
                    results.append("Snippet: ").append(snippet).append("\n\n");
                }
            } else {
                results.append("No results found.");
            }
        } catch (JSONException e) {
            results.append("please enter a query.");
        }
        return results.toString();
    }
}

