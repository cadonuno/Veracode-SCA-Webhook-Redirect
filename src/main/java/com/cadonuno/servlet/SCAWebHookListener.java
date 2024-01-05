package com.cadonuno.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cadonuno.LogHandler;
import com.cadonuno.webhookhandlers.WebhookHandlers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.cadonuno.webrequest.MessageSubmitter;

public class SCAWebHookListener extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try (InputStream inputStream = request.getInputStream()) {
            VeracodeWebhookRequest.Root webhookRequest = new ObjectMapper().readValue(inputStream, VeracodeWebhookRequest.Root.class);
            WebRequestResult webRequestResult = WebhookHandlers.get(webhookRequest.event)
                    .flatMap(webhookHandler -> webhookHandler.getWebhookMessage(webhookRequest))
                    .map(MessageSubmitter::submitRequest)
                    .orElse(new WebRequestResult(422, "Invalid input"));
            response.setStatus(webRequestResult.getReturnCode());
            writeOutput(response, webRequestResult.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeOutput(HttpServletResponse response, String message) {
        try (OutputStream outputStream = response.getOutputStream();
             PrintWriter printWriter = new PrintWriter(outputStream, true)) {
            printWriter.print(message);
        } catch (IOException e) {
            LogHandler.error("Unable to return request status", "Unable to write to output stream", e);
        }
    }
}
