package com.cadonuno.webhookhandlers;

import java.util.Optional;

public final class WebhookHandlers {
    private static final String SCAN_SUCCESS = "SCAN_SUCCESS";
    private static final String VULN_ISSUES_DISCOVERED_AFTER_SCAN = "VULN_ISSUES_DISCOVERED_AFTER_SCAN";
    private static final String VULN_ISSUES_CHANGED_AFTER_SCAN = "VULN_ISSUES_CHANGED_AFTER_SCAN";
    private WebhookHandlers() {
        throw new IllegalStateException("");
    }

    public static Optional<WebhookHandler> get(String event) {
        switch (event) {
            case SCAN_SUCCESS:
                return Optional.of(new ScanSuccessWebhookHandler());
            case VULN_ISSUES_DISCOVERED_AFTER_SCAN:
                return Optional.of(new IssueDiscoveredAfterScanWebhookHandler());
            case VULN_ISSUES_CHANGED_AFTER_SCAN:
                return Optional.of(new IssueChangedAfterScanWebhookHandler());
        }
        return Optional.empty();
    }
}
