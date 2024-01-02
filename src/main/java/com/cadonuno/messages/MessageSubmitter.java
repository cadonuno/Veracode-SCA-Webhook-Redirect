package com.cadonuno.messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.cadonuno.LogHandler;

public class MessageSubmitter {
    private static final String POST = "POST";

    public static void submitRequest(TeamsMessage teamsMessage) {
        try {
            HttpURLConnection httpConnection = getHttpURLConnection(teamsMessage);
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(httpConnection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }
                LogHandler.debug("Submitted request to " + teamsMessage.getEndpointUrl() + " received response of: ", response.toString());
            }
        } catch (IOException e) {
            LogHandler.error("Error when submitting request to " + teamsMessage.getEndpointUrl(), e.getMessage(), e);
        }
    }

    private static HttpURLConnection getHttpURLConnection(TeamsMessage teamsMessage) throws IOException {
        URL url = new URL(teamsMessage.getEndpointUrl());
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod(POST);
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setRequestProperty("Accept", "application/json");
        httpConnection.setDoOutput(true);
        String jsonInputString = "{\"text\": \"" + teamsMessage.getMessageToSend() + "\"}";
        try (OutputStream outputStream = httpConnection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }
        return httpConnection;
    }
}
