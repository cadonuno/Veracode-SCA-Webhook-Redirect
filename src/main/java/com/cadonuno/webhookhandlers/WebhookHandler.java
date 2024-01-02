package com.cadonuno.webhookhandlers;

import com.cadonuno.messages.EndpointsMap;
import com.cadonuno.messages.TeamsMessage;
import com.cadonuno.servlet.VeracodeWebhookRequest;

import java.util.Optional;

public interface WebhookHandler {
    default Optional<TeamsMessage> getTeamsMessage(VeracodeWebhookRequest.Root webhookRequest) {
        return EndpointsMap.getEndpointFor(
                        webhookRequest.workspace.id,
                        webhookRequest.project.id)
                .map(endpointUrl ->
                        TeamsMessage.of(endpointUrl,
                                getMessageTitle(),
                                getMessageBody(webhookRequest)));
    }
    static String makeLink(String link) {
        return "<a href=" + link + ">" + link + "</a>";
    }

    String getMessageTitle();

    String getMessageBody(VeracodeWebhookRequest.Root webhookRequest);
}
