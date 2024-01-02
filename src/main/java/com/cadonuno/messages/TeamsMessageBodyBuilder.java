package com.cadonuno.messages;

public class TeamsMessageBodyBuilder {
    public static String buildMessageFor(ListFieldView... listFieldView) {
        return "<ul>" + buildList(listFieldView) + "</ul>";
    }

    private static String buildList(ListFieldView[] listFieldViews) {
        StringBuilder listBuilder = new StringBuilder();
        for (ListFieldView listFieldView : listFieldViews) {
            listBuilder.append("<li>")
                    .append(listFieldView.getTitle()).append(": ")
                    .append(listFieldView.getValue())
                    .append("</li>");
        }
        return listBuilder.toString();
    }
}
