package ru.otus.message.messagesystem;

public enum MessageType {
    USER_DATA("UserData"),
    ALL_DATA("AllData"),
    ONE_DATA("OneData"),
    SAVE_DATA("SaveData");

    private final String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
