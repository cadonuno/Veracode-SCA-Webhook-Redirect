package com.cadonuno;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;

public class LogHandler {
    private static final Logger LOGGER = ESAPI.getLogger(LogHandler.class);

    public static void debug(String title, String message) {
        LOGGER.debug(Logger.SECURITY_SUCCESS, title);
        LOGGER.debug(Logger.SECURITY_SUCCESS, message);
    }

    public static void error(String title, String message, Throwable throwable) {
        LOGGER.error(Logger.SECURITY_SUCCESS, title);
        LOGGER.error(Logger.SECURITY_SUCCESS, message, throwable);
    }
}
