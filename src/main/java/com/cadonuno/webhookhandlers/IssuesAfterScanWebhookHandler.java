package com.cadonuno.webhookhandlers;

import com.cadonuno.messages.ListFieldView;
import com.cadonuno.messages.TeamsMessageBodyBuilder;
import com.cadonuno.servlet.VeracodeWebhookRequest;

import java.util.ArrayList;

public interface IssuesAfterScanWebhookHandler extends WebhookHandler {
    @Override
    default String getMessageBody(VeracodeWebhookRequest.Root webhookRequest) {
        return TeamsMessageBodyBuilder.buildMessageFor(getIssuesAsListFieldView(webhookRequest.issues));
    }

    default ListFieldView[] getIssuesAsListFieldView(ArrayList<VeracodeWebhookRequest.Issue> issues) {
        return issues.stream()
                .map(this::issueToListFieldView)
                .toArray(ListFieldView[]::new);
    }

    default ListFieldView issueToListFieldView(VeracodeWebhookRequest.Issue issue) {
        VeracodeWebhookRequest.Vuln vulnerability = issue.vuln;
        return ListFieldView.of("Issue " + issue.id,
                TeamsMessageBodyBuilder.buildMessageFor(
                        ListFieldView.of("Status", issue.status),
                        ListFieldView.of("Issue URL", WebhookHandler.makeLink(issue.issueUrl)),
                        ListFieldView.of("Vulnerability ID", vulnerability.id),
                        ListFieldView.of("Vulnerability title", vulnerability.title),
                        ListFieldView.of("Vulnerability CVSS 2", vulnerability.cvssScore),
                        ListFieldView.of("Vulnerability CVSS 3", vulnerability.cvss3Score),
                        ListFieldView.of("Vulnerability CVE", vulnerability.cve),
                        ListFieldView.of("Vulnerability CVE Status", vulnerability.cveStatus),
                        ListFieldView.of("Vulnerability stage", vulnerability.stage),
                        ListFieldView.of("Vulnerability disclosure date", vulnerability.disclosureDate),
                        ListFieldView.of("Vulnerability has exploits", vulnerability.hasExploits),
                        ListFieldView.of("Vulnerability types",
                                vulnerabilityTypesToMessage(vulnerability.vulnerabilityTypes)),
                        ListFieldView.of("Vulnerability overview", vulnerability.overview)));
    }

    default String vulnerabilityTypesToMessage(ArrayList<String> vulnerabilityTypes) {
        return String.join(", ", vulnerabilityTypes);
    }
}
