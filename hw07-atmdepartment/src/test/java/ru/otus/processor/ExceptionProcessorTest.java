package ru.otus.processor;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import ru.otus.Message;
import ru.otus.time.TimeProvider;
import ru.otus.time.TimeProviderTest;

class ExceptionProcessorTest {

    @Test
    void process() {
        TimeProvider timeProvider = TimeProviderTest.newInstance();
        timeProvider.getCurrentDate();
        Processor processor = new ExceptionProcessor(new ProcessorSwap11and12(), timeProvider);
        Message message = processor.process(new Message.Builder()
                .field1("field1")
                .field11("field11")
                .field12("field12")
                .build()
        );
        System.out.println(message);
    }
}