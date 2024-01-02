package com.cadonuno;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHandler {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void debug(String title, String message) {
        LOGGER.debug(title + "\n" + message);
    }

    public static void error(String title, String message, Throwable throwable) {
        LOGGER.error(title + "\n" + message, throwable);
    }
}
