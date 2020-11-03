package ru.otus.processor;

import ru.otus.Message;

public class ProcessorSwap11and12 implements Processor {

    @Override
    public Message process(Message message) {
        var messageFrom11 = message.getField11();
        var messageFrom12 = message.getField12();
        return message.toBuilder().field11(messageFrom12).field12(messageFrom11).build();
    }

}
