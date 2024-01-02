package com.cadonuno.messages;

public class ConfigurationLine {
    private final int workspaceId;
    private final int projectId;
    private final String endpointUrl;

    public ConfigurationLine(int workspaceId, int projectId, String endpointUrl) {
        this.workspaceId = workspaceId;
        this.projectId = projectId;
        this.endpointUrl = endpointUrl;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }
}
