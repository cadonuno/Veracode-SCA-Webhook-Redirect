package com.cadonuno.messages;


public class TeamsMessage {
    private final String endpointUrl;
    private final String messageToSend;

    private TeamsMessage(String endpointUrl, String messageToSend) {
        this.endpointUrl = endpointUrl;
        this.messageToSend = messageToSend;
    }

    public static TeamsMessage of(String endpointUrl, String messageTitle, String messageBody) {
        return new TeamsMessage(endpointUrl, buildMessageHtml(messageTitle, messageBody));
    }

    private static String buildMessageHtml(String messageTitle, String messageBody) {
        return "<h3>" + messageTitle + "</h3> <br/>" +
                "<h2>" + messageBody + "</h2>";
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public String getMessageToSend() {
        return messageToSend;
    }
}
