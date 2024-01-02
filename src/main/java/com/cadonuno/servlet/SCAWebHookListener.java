package com.cadonuno.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cadonuno.webhookhandlers.WebhookHandlers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

import com.cadonuno.messages.MessageSubmitter;

public class SCAWebHookListener extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try (InputStream inputStream = request.getInputStream()) {
            VeracodeWebhookRequest.Root webhookRequest = new ObjectMapper().readValue(inputStream, VeracodeWebhookRequest.Root.class);
            WebhookHandlers.get(webhookRequest.event)
                    .flatMap(webhookHandler -> webhookHandler.getTeamsMessage(webhookRequest))
                    .ifPresent(MessageSubmitter::submitRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
