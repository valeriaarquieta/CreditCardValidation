package com.example.levelup.fetch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchCard {
    public static void main(String[] args) {
        try {
           
            URL url = new URL("http://127.0.0.1:5500/card.html");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            String creditCardData = "{\"dateExpiry\":\"12/25\", \"cvv\":\"123\", \"pan\":\"1234567890123456\"}";
            connection.setRequestProperty("Content-Type", "application/json");

           
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(creditCardData.getBytes());
            outputStream.flush();

           
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

          
            System.out.println(response.toString());

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
