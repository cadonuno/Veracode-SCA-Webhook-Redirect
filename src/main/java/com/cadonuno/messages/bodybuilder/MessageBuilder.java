package com.cadonuno.messages.bodybuilder;

import com.cadonuno.messages.endpoints.TeamsWebhookEndpoint;
import com.cadonuno.messages.endpoints.WebhookEndpoint;

public interface MessageBuilder {
    static MessageBuilder get(WebhookEndpoint endpoint) {
        return endpoint instanceof TeamsWebhookEndpoint
                ? new TeamsMessageBuilder()
                : new SlackMessageBuilder();
    }

    String buildMessageFor(MessageListElement... messageListElements);

    String buildMessageContent(String messageTitle, String messageBody);
}
