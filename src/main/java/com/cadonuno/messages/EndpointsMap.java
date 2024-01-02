package com.cadonuno.messages;

import com.cadonuno.LogHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class EndpointsMap {
    private static final Map<Integer, Map<Integer, String>> endpointsMap = initializeEndpointsMap();

    //TODO: point this to your configuration file
    private static final String CONFIGURATION_FILE = "C:\\EndpointsMap\\webhooks";

    private static Map<Integer, Map<Integer, String>> initializeEndpointsMap() {
        Map<Integer, Map<Integer, String>> endpointsMap = new HashMap<>();
        File initializationFile = new File(CONFIGURATION_FILE);
        try (Scanner scanner = new Scanner(initializationFile)) {
            while (scanner.hasNextLine()) {
                tryReadConfigurationLine(scanner.nextLine())
                        .ifPresent(configurationLine -> {
                            if (endpointsMap.containsKey(configurationLine.getWorkspaceId())) {
                                endpointsMap.get(configurationLine.getWorkspaceId())
                                        .put(configurationLine.getProjectId(), configurationLine.getEndpointUrl());
                            } else {
                                endpointsMap.put(configurationLine.getWorkspaceId(), new HashMap<Integer, String>() {{
                                    put(configurationLine.getProjectId(), configurationLine.getEndpointUrl());
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
        if (splitLine.length != 3) {
            return Optional.empty();
        }
        try {
            int workspaceId = Integer.parseInt(splitLine[0]);
            int projectId = Integer.parseInt(splitLine[1]);
            return Optional.of(new ConfigurationLine(workspaceId, projectId, splitLine[2]));
        } catch (NumberFormatException nfe) {
            LogHandler.error("Unable to parse configuration line '" + line + "'", nfe.getMessage(), nfe);
            return Optional.empty();
        }
    }

    public static Optional<String> getEndpointFor(int workspaceId, int projectId) {
        return Optional.ofNullable(endpointsMap.get(workspaceId))
                .flatMap(workspaceMap -> Optional.ofNullable(workspaceMap.get(projectId)));
    }


}
