package ru.otus.time;

import java.util.Calendar;

public class TimeProviderTest implements TimeProvider {
    private final Calendar currentDate = Calendar.getInstance();

    private TimeProviderTest() {
        currentDate.set(Calendar.SECOND, 30);
    }

    public static TimeProviderTest newInstance() {
        return new TimeProviderTest();
    }

    @Override
    public Calendar getCurrentDate() {
        return currentDate;
    }
}
