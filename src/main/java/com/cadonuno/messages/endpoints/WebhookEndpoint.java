package com.cadonuno.messages.endpoints;

public abstract class WebhookEndpoint {
    private final String endpointUrl;

    public WebhookEndpoint(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    static WebhookEndpoint get(String endpointUrl, String endpointType) {
        if (endpointType == null) {
            return new TeamsWebhookEndpoint(endpointUrl);
        }
        return "teams".equalsIgnoreCase(endpointType)
                ? new TeamsWebhookEndpoint(endpointUrl)
                : new SlackWebhookEndpoint(endpointUrl);
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }
}
