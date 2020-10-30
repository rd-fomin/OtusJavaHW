package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerHistory;
import ru.otus.listener.ListenerPrinter;
import ru.otus.processor.*;
import ru.otus.time.TimeProvider;
import ru.otus.time.TimeProviderTest;
import ru.otus.time.TimeProviderWork;

import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
        TimeProvider timeProvider = TimeProviderWork.newInstance();
        List<Processor> processors = List.of(new ExceptionProcessor(new ProcessorSwap11and12(), timeProvider));

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {});
        var listenerPrinter = new ListenerPrinter();
        var listenerHistory = new ListenerHistory();
        complexProcessor.addListener(listenerPrinter);
        complexProcessor.addListener(listenerHistory);

        var obj = new ObjectForMessage();
        obj.setData(List.of("field13.1", "field13.2"));

        var message = new Message.Builder()
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .field13(obj)
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        complexProcessor.removeListener(listenerPrinter);
        complexProcessor.removeListener(listenerHistory);
    }
}
