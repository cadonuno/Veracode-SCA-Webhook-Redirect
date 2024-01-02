package com.cadonuno.webhookhandlers;

public class IssueChangedAfterScanWebhookHandler implements IssuesAfterScanWebhookHandler {
    @Override
    public String getMessageTitle() {
        return "Issues Changed After Scan";
    }
}
