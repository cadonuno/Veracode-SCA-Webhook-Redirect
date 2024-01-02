package com.cadonuno.messages;

public class ListFieldView {
    private final String title;
    private final String value;

    private ListFieldView(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public static ListFieldView of(String title, Object value) {
        return new ListFieldView(title, value == null ? "" : value.toString());
    }

    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
}
