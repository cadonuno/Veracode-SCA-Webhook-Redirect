package com.cadonuno.messages.endpoints;

import com.cadonuno.LogHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class EndpointsMap {
    private static final Map<Integer, Map<Integer, WebhookEndpoint>> endpointsMap = initializeEndpointsMap();

    //TODO: point this to your configuration file
    private static final String CONFIGURATION_FILE = "C:\\EndpointsMap\\webhooks";

    private static Map<Integer, Map<Integer, WebhookEndpoint>> initializeEndpointsMap() {
        Map<Integer, Map<Integer, WebhookEndpoint>> endpointsMap = new HashMap<>();
        File initializationFile = new File(CONFIGURATION_FILE);
        try (Scanner scanner = new Scanner(initializationFile)) {
            while (scanner.hasNextLine()) {
                tryReadConfigurationLine(scanner.nextLine())
                        .ifPresent(configurationLine -> {
                            if (endpointsMap.containsKey(configurationLine.getWorkspaceId())) {
                                endpointsMap.get(configurationLine.getWorkspaceId())
                                        .put(configurationLine.getProjectId(), configurationLine.getEndpoint());
                            } else {
                                endpointsMap.put(configurationLine.getWorkspaceId(), new HashMap<Integer, WebhookEndpoint>() {{
                                    put(configurationLine.getProjectId(), configurationLine.getEndpoint());
                                }});
                            }
                        });
            }
        } catch (FileNotFoundException e) {
            LogHandler.error("Unable to open configuration file at '" + CONFIGURATION_FILE + "'", e.getMessage(), e);
        }
        return endpointsMap;
    }

    private static Optional<ConfigurationLine> tryReadConfigurationLine(String line) {
        String[] splitLine = line.split(" ");
        int length = splitLine.length;
        if (length < 3 || length > 4) {
            LogHandler.debug("Unable to parse configuration line '" + line + "'", "Line has wrong number of elements (expected 3 or 4)");
            return Optional.empty();
        }
        try {
            return Optional.of(new ConfigurationLine(Integer.parseInt(splitLine[0]),
                    Integer.parseInt(splitLine[1]),
                    splitLine[2],
                    length == 3 ? null : splitLine[3]));
        } catch (NumberFormatException nfe) {
            LogHandler.error("Unable to parse configuration line '" + line + "'", nfe.getMessage(), nfe);
            return Optional.empty();
        }
    }

    public static Optional<WebhookEndpoint> getEndpointFor(int workspaceId, int projectId) {
        return Optional.ofNullable(endpointsMap.get(workspaceId))
                .flatMap(workspaceMap -> Optional.ofNullable(workspaceMap.get(projectId)));
    }


}
