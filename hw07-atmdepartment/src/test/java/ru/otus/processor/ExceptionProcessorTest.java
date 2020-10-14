package ru.otus.processor;

import org.junit.jupiter.api.RepeatedTest;
import ru.otus.Message;

class ExceptionProcessorTest {

    @RepeatedTest(2)
    void process() {
        Processor processor = new ExceptionProcessor(new ProcessorSwap11and12());
        Message message = processor.process(new Message.Builder()
                .field1("field1")
                .field11("field11")
                .field12("field12")
                .build()
        );
        System.out.println(message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}