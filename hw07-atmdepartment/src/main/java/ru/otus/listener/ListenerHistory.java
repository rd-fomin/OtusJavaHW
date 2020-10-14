package ru.otus.listener;

import ru.otus.Message;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenerHistory implements Listener {
    public static final List<Map.Entry<Message, Message>> history = new ArrayList<>();

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        history.add(new AbstractMap.SimpleEntry<>(oldMsg, newMsg));
    }

}
