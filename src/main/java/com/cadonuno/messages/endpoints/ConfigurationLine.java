package com.cadonuno.messages.endpoints;

public class ConfigurationLine {
    private final int workspaceId;
    private final int projectId;
    private final WebhookEndpoint endpoint;

    public ConfigurationLine(int workspaceId, int projectId, String endpointUrl, String endpointType) {
        this.workspaceId = workspaceId;
        this.projectId = projectId;
        this.endpoint = WebhookEndpoint.get(endpointUrl, endpointType);
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public int getProjectId() {
        return projectId;
    }

    public WebhookEndpoint getEndpoint() {
        return endpoint;
    }
}
