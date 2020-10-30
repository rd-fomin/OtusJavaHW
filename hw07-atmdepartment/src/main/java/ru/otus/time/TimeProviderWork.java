package ru.otus.time;

import java.util.Calendar;

public class TimeProviderWork implements TimeProvider {
    private final Calendar currentDate = Calendar.getInstance();

    private TimeProviderWork() {
        currentDate.set(Calendar.SECOND, 30);
    }

    public static TimeProviderWork newInstance() {
        return new TimeProviderWork();
    }

    @Override
    public Calendar getCurrentDate() {
        return currentDate;
    }

}
