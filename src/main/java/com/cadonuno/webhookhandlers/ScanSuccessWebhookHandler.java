package com.cadonuno.webhookhandlers;

import com.cadonuno.messages.ListFieldView;
import com.cadonuno.messages.TeamsMessageBodyBuilder;
import com.cadonuno.servlet.VeracodeWebhookRequest;

public class ScanSuccessWebhookHandler implements WebhookHandler {
    @Override
    public String getMessageTitle() {
        return "Scan Success";
    }

    @Override
    public String getMessageBody(VeracodeWebhookRequest.Root webhookRequest) {
        VeracodeWebhookRequest.Scan scan = webhookRequest.scan;
        return TeamsMessageBodyBuilder.buildMessageFor(
                ListFieldView.of("Scan ID", scan.id),
                ListFieldView.of("Commit Hash", scan.commit),
                ListFieldView.of("Branch", scan.branch),
                ListFieldView.of("Tag", scan.tag),
                ListFieldView.of("Report link", WebhookHandler.makeLink(scan.reportLink)),
                ListFieldView.of("Number of vulnerabilities", scan.vulnIssuesCount),
                ListFieldView.of("Number of out of date libraries", scan.outofDateIssuesCount),
                ListFieldView.of("Number of license issues", scan.licenseIssuesCount));
    }
}
