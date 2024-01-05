package com.cadonuno.webrequest;


import com.cadonuno.messages.bodybuilder.MessageBuilder;
import com.cadonuno.messages.endpoints.WebhookEndpoint;

public class WebhookMessage {
    private final WebhookEndpoint webhookEndpoint;
    private final String messageToSend;

    private WebhookMessage(WebhookEndpoint webhookEndpoint, String messageToSend) {
        this.webhookEndpoint = webhookEndpoint;
        this.messageToSend = messageToSend;
    }

    public static WebhookMessage of(WebhookEndpoint webhookEndpoint, String messageTitle, String messageBody) {
        return new WebhookMessage(webhookEndpoint, MessageBuilder.get(webhookEndpoint).buildMessageContent(messageTitle, messageBody));
    }


    public String getMessageToSend() {
        return messageToSend;
    }

    public String getEndpointUrl() {
        return webhookEndpoint.getEndpointUrl();
    }
}
