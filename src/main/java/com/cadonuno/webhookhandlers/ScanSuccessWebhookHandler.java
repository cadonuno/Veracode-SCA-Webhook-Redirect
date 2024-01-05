package com.cadonuno.webhookhandlers;

import com.cadonuno.messages.bodybuilder.MessageBuilder;
import com.cadonuno.messages.bodybuilder.MessageListElement;
import com.cadonuno.messages.endpoints.WebhookEndpoint;
import com.cadonuno.servlet.VeracodeWebhookRequest;

public class ScanSuccessWebhookHandler implements WebhookHandler {
    @Override
    public String getMessageTitle() {
        return "Scan Success";
    }

    @Override
    public String getMessageBody(WebhookEndpoint endpoint, VeracodeWebhookRequest.Root webhookRequest) {
        VeracodeWebhookRequest.Scan scan = webhookRequest.scan;
        return MessageBuilder.get(endpoint).buildMessageFor(
                MessageListElement.of("Scan ID", scan.id),
                MessageListElement.of("Commit Hash", scan.commit),
                MessageListElement.of("Branch", scan.branch),
                MessageListElement.of("Tag", scan.tag),
                MessageListElement.of("Report link", scan.reportLink),
                MessageListElement.of("Number of vulnerabilities", scan.vulnIssuesCount),
                MessageListElement.of("Number of out of date libraries", scan.outofDateIssuesCount),
                MessageListElement.of("Number of license issues", scan.licenseIssuesCount));
    }
}
