package ru.otus.processor;

import ru.otus.Message;

import java.util.Calendar;

public class ExceptionProcessor implements Processor {
    private final Processor processor;

    public ExceptionProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Message process(Message message) {
        if (Calendar.getInstance().get(Calendar.SECOND) % 2 == 0)
            throw new RuntimeException("It's even second!");
        return processor.process(message);
    }
}
