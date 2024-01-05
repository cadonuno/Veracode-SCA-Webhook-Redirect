package com.cadonuno.messages.bodybuilder;

import java.util.Locale;

public class TeamsMessageBuilder implements MessageBuilder {
    @Override
    public String buildMessageFor(MessageListElement... messageListElements) {
        return "<ul>" + buildList(messageListElements) + "</ul>";
    }

    public String buildList(MessageListElement[] messageListElements) {
        StringBuilder listBuilder = new StringBuilder();
        for (MessageListElement messageListElement : messageListElements) {
            listBuilder.append("<li><b>")
                    .append(messageListElement.getTitle()).append("</b>: ");
            if (messageListElement.getValue() != null) {
                listBuilder.append(formatValue(messageListElement.getValue()));
            } else {
                listBuilder.append(buildMessageFor(messageListElement.getChainedListViews()));
            }
            listBuilder.append("</li>");
        }
        return listBuilder.toString();
    }

    private String formatValue(String value) {
        return value.trim().toLowerCase(Locale.ROOT).startsWith("http:")
                ? makeLink(value)
                : value;
    }

    private static String makeLink(String link) {
        return "<a href=" + link + ">" + link + "</a>";
    }

    @Override
    public String buildMessageContent(String messageTitle, String messageBody) {
        return "{\"text\": \"" +
                "<h3>" + messageTitle + "</h3> <br/>" +
                messageBody +
                "\"}";
    }
}
