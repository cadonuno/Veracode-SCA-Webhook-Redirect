package com.cadonuno.webhookhandlers;

public class IssueDiscoveredAfterScanWebhookHandler implements IssuesAfterScanWebhookHandler {
    @Override
    public String getMessageTitle() {
        return "Issues Discovered After Scan";
    }
}
