package com.cadonuno.webhookhandlers;

import com.cadonuno.messages.bodybuilder.MessageBuilder;
import com.cadonuno.messages.bodybuilder.MessageListElement;
import com.cadonuno.messages.endpoints.WebhookEndpoint;
import com.cadonuno.servlet.VeracodeWebhookRequest;

import java.util.ArrayList;

public interface IssuesAfterScanWebhookHandler extends WebhookHandler {
    @Override
    default String getMessageBody(WebhookEndpoint webhookEndpoint, VeracodeWebhookRequest.Root webhookRequest) {
        return MessageBuilder.get(webhookEndpoint).buildMessageFor(getIssuesAsMessageListElement(webhookRequest.issues));
    }

    default MessageListElement[] getIssuesAsMessageListElement(ArrayList<VeracodeWebhookRequest.Issue> issues) {
        return issues.stream()
                .map(this::issueToListFieldView)
                .toArray(MessageListElement[]::new);
    }

    default MessageListElement issueToListFieldView(VeracodeWebhookRequest.Issue issue) {
        VeracodeWebhookRequest.Vuln vulnerability = issue.vuln;
        return MessageListElement.of("Issue " + issue.id,
                WebhookHandler.buildChainedListView(
                        MessageListElement.of("Status", issue.status),
                        MessageListElement.of("Issue URL", issue.issueUrl),
                        MessageListElement.of("Vulnerability ID", vulnerability.id),
                        MessageListElement.of("Vulnerability title", vulnerability.title),
                        MessageListElement.of("Vulnerability CVSS 2", vulnerability.cvssScore),
                        MessageListElement.of("Vulnerability CVSS 3", vulnerability.cvss3Score),
                        MessageListElement.of("Vulnerability CVE", vulnerability.cve),
                        MessageListElement.of("Vulnerability CVE Status", vulnerability.cveStatus),
                        MessageListElement.of("Vulnerability stage", vulnerability.stage),
                        MessageListElement.of("Vulnerability disclosure date", vulnerability.disclosureDate),
                        MessageListElement.of("Vulnerability has exploits", vulnerability.hasExploits),
                        MessageListElement.of("Vulnerability types",
                                vulnerabilityTypesToMessage(vulnerability.vulnerabilityTypes)),
                        MessageListElement.of("Vulnerability overview", vulnerability.overview)));
    }

    default String vulnerabilityTypesToMessage(ArrayList<String> vulnerabilityTypes) {
        return String.join(", ", vulnerabilityTypes);
    }
}
