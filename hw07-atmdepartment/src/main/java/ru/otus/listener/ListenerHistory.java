package ru.otus.listener;

import ru.otus.Message;
import ru.otus.listener.homework.History;

import java.util.ArrayList;
import java.util.List;

public class ListenerHistory implements Listener {
    public static final List<History> history = new ArrayList<>();

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        history.add(new History(oldMsg, newMsg));
    }

}
