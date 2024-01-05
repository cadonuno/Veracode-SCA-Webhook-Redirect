package com.cadonuno.messages.bodybuilder;

public class SlackMessageBuilder implements MessageBuilder {

    private static final String LEVEL_START = "       ";

    @Override
    public String buildMessageFor(MessageListElement... messageListElements) {
        return buildList(messageListElements, 0);
    }

    public String buildList(MessageListElement[] messageListElements, int level) {
        StringBuilder listBuilder = new StringBuilder();
        for (MessageListElement messageListElement : messageListElements) {
            listBuilder.append("\\n");
            for (int currentLevel = 0; currentLevel < level; currentLevel++) {
                listBuilder.append(LEVEL_START);
            }
            listBuilder.append("* *")
                    .append(messageListElement.getTitle()).append("*: ");
            if (messageListElement.getValue() != null) {
                listBuilder.append(messageListElement.getValue());
            } else {
                listBuilder.append(buildList(messageListElement.getChainedListViews(), level + 1));
            }
        }
        return listBuilder.toString();
    }

    @Override
    public String buildMessageContent(String messageTitle, String messageBody) {
        return "{" +
                "\"blocks\": [" +
                "{" +
                "\"type\": \"section\"," +
                "\"text\": {" +
                "\"type\": \"mrkdwn\"," +
                "\"text\": \"*" + messageTitle + "*\\n\\n\\n" +
                messageBody + "\"" +
                "}" +
                "}" +
                "]" +
                "}";
    }
}
