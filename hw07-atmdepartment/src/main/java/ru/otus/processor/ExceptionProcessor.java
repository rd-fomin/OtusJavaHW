package ru.otus.processor;

import ru.otus.Message;
import ru.otus.time.TimeProvider;

import java.util.Calendar;

public class ExceptionProcessor implements Processor {
    private final Processor processor;
    private final TimeProvider timeProvider;

    public ExceptionProcessor(Processor processor, TimeProvider timeProvider) {
        this.processor = processor;
        this.timeProvider = timeProvider;
    }

    @Override
    public Message process(Message message) {
        if (timeProvider.getCurrentDate().get(Calendar.SECOND) % 2 == 0)
            throw new RuntimeException("It's even second!");
        return processor.process(message);
    }
}
