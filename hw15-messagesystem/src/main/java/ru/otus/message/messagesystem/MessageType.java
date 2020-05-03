package ru.otus.message.messagesystem;

public enum MessageType {
    GET_ALL("GetAll"),
    GET_BY_ID("GetById"),
    GET_BY_LOGIN("GetByLogin"),
    SAVE_USER("SaveUser");

    private final String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
