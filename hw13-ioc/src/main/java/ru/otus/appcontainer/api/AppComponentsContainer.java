package ru.otus.appcontainer.api;

public interface AppComponentsContainer {
    <C> C getAppComponent(Class<C> componentClass) throws IllegalArgumentException;
    <C> C getAppComponent(String componentName) throws ClassNotFoundException;
}
