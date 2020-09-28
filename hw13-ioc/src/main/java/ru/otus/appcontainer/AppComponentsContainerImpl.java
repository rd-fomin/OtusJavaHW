package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.appcontainer.api.AppException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws AppException {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws AppException {
        checkConfigClass(configClass);
        // You code here...
        List<Method> methods = Arrays.asList(configClass.getDeclaredMethods());
        methods.sort(Comparator.comparing(method -> method.getDeclaredAnnotation(AppComponent.class).order()));

        Object objForMethod;
        try {
            objForMethod = configClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new AppException("Couldn't create object for class" + configClass.getCanonicalName());
        }

        methods.forEach(method -> {
            try {
                createObject(method, objForMethod);
            } catch (AppException e) {
                e.printStackTrace();
            }
        });

    }

    private void createObject(Method methodFromObj, Object objForMethod) throws AppException {
        Class<?>[] parameters = methodFromObj.getParameterTypes();
        List<Object> objects = new ArrayList<>();
        for (Class<?> parameter : parameters) {
            Object obj = getAppComponent(parameter);
            objects.add(obj);
        }
        Object appComponent;
        try {
            appComponent = methodFromObj.invoke(objForMethod, objects.toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException("Couldn't invoke method " + methodFromObj.getName());
        }
        appComponents.add(appComponent);
        appComponentsByName.put(methodFromObj.getName(), appComponent);
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) throws IllegalArgumentException {
        for (Object o : appComponents) {
            if (Arrays.stream(o.getClass().getInterfaces()).anyMatch(aClass -> aClass.isAssignableFrom(componentClass)))
                return componentClass.cast(o);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
