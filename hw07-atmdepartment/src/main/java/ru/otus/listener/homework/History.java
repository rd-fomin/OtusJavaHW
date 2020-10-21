package ru.otus.listener.homework;

import ru.otus.Message;

public class History {
    private final Message oldMsg;
    private final Message newMsg;

    public History(Message oldMsg, Message newMsg) {
        this.oldMsg = oldMsg;
        this.newMsg = newMsg;
    }

    public Message getOldMsg() {
        return oldMsg;
    }

    public Message getNewMsg() {
        return newMsg;
    }
}
