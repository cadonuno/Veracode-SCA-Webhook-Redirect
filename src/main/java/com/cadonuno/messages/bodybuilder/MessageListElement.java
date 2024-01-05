package com.cadonuno.messages.bodybuilder;

public class MessageListElement {
    private final String title;
    private final String value;
    private final MessageListElement[] chainedListViews;

    private MessageListElement(String title, String value) {
        this.title = title;
        this.value = value;
        this.chainedListViews = null;
    }

    public MessageListElement(String title, MessageListElement[] chainedListViews) {
        this.title = title;
        this.value = null;
        this.chainedListViews = chainedListViews;
    }

    public static MessageListElement of(String title, MessageListElement[] chainedListViews) {
        return new MessageListElement(title, chainedListViews);
    }

    public static MessageListElement of(String title, Object value) {
        return new MessageListElement(title, value == null ? "" : value.toString());
    }

    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }

    public MessageListElement[] getChainedListViews() {
        return chainedListViews;
    }
}
