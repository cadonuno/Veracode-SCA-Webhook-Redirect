package com.cadonuno.webrequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.cadonuno.LogHandler;
import com.cadonuno.servlet.WebRequestResult;

public class MessageSubmitter {
    private static final String POST = "POST";

    public static WebRequestResult submitRequest(WebhookMessage webhookMessage) {
        try {
            HttpURLConnection httpConnection = getHttpURLConnection(webhookMessage);
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(httpConnection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }
                LogHandler.debug("Submitted request to " + webhookMessage.getEndpointUrl() + " received response of: ", response.toString());
                return new WebRequestResult(201, "Request submitted");
            }
        } catch (IOException e) {
            LogHandler.error("Error when submitting request to " + webhookMessage.getEndpointUrl(), e.getMessage(), e);
            return new WebRequestResult(500, "Error sending message");
        }
    }

    private static HttpURLConnection getHttpURLConnection(WebhookMessage webhookMessage) throws IOException {
        URL url = new URL(webhookMessage.getEndpointUrl());
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod(POST);
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setRequestProperty("Accept", "application/json");
        httpConnection.setDoOutput(true);
        String jsonInputString = webhookMessage.getMessageToSend();
        try (OutputStream outputStream = httpConnection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }
        return httpConnection;
    }
}
