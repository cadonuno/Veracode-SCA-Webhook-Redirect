package com.cadonuno.webhookhandlers;

import com.cadonuno.messages.bodybuilder.MessageListElement;
import com.cadonuno.messages.endpoints.EndpointsMap;
import com.cadonuno.webrequest.WebhookMessage;
import com.cadonuno.messages.endpoints.WebhookEndpoint;
import com.cadonuno.servlet.VeracodeWebhookRequest;

import java.util.Optional;

public interface WebhookHandler {

    default Optional<WebhookMessage> getWebhookMessage(VeracodeWebhookRequest.Root webhookRequest) {
        return EndpointsMap.getEndpointFor(
                        webhookRequest.workspace.id,
                        webhookRequest.project.id)
                .map(webhookEndpoint ->
                        WebhookMessage.of(webhookEndpoint,
                                getMessageTitle(),
                                getMessageBody(webhookEndpoint, webhookRequest)));
    }

    static MessageListElement[] buildChainedListView(MessageListElement... messageListElements) {
        return messageListElements;
    }

    String getMessageTitle();

    String getMessageBody(WebhookEndpoint endpoint, VeracodeWebhookRequest.Root webhookRequest);
}
