package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        checkConfigClass(configClass);
        // You code here...
        List<Method> methods = Arrays.asList(configClass.getDeclaredMethods());
        methods.sort(Comparator.comparing(method -> method.getDeclaredAnnotation(AppComponent.class).order()));

        Object objForMethod = configClass.getConstructor().newInstance();

        methods.forEach(method -> {
            try {
                createObject(method, objForMethod);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        });

    }

    private void createObject(Method methodFromObj, Object objForMethod) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?>[] parameters = methodFromObj.getParameterTypes();
        if (parameters.length == 0) {
            Object appComponent = methodFromObj.invoke(objForMethod);
            appComponents.add(appComponent);
            appComponentsByName.put(methodFromObj.getName(), appComponent);
        } else {
            List<Object> objects = new ArrayList<>();
            for (Class<?> parameter : parameters) {
                Object obj = appComponents.stream()
                        .filter(o -> Arrays.stream(o.getClass().getInterfaces())
                                .findFirst()
                                .filter(o1 -> o1.getCanonicalName().equals(parameter.getCanonicalName()))
                                .isPresent()
                        )
                        .findFirst().orElseThrow();
                objects.add(obj);
            }
            Object appComponent = methodFromObj.invoke(objForMethod, objects.toArray());
            appComponents.add(appComponent);
            appComponentsByName.put(methodFromObj.getName(), appComponent);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        for (Object o : appComponents) {
            if (Arrays.stream(o.getClass().getInterfaces()).anyMatch(aClass -> aClass.getCanonicalName().equals(componentClass.getCanonicalName())))
                return componentClass.cast(o);
        }
        return null;
    }

    @Override
    public <C> C getAppComponent(String componentName) throws ClassNotFoundException {
        return (C) appComponentsByName.get(componentName);
    }
}
