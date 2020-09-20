package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.services.EquationPreparer;
import ru.otus.services.GameProcessorImpl;
import ru.otus.services.IOService;
import ru.otus.services.PlayerService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        // You code here...
        List<Method> methods = Arrays.asList(configClass.getDeclaredMethods());
        methods.sort(Comparator.comparing(method -> method.getDeclaredAnnotation(AppComponent.class).order()));
        Map<String, Method> methodMap = new HashMap<>();
        methods.forEach(method -> methodMap.put(method.getName(), method));
        try {
            Object obj = configClass.getConstructor().newInstance();
            EquationPreparer eP = (EquationPreparer) methodMap.get("equationPreparer").invoke(obj);
            IOService ioService = (IOService) methodMap.get("ioService").invoke(obj);
            PlayerService playerService = (PlayerService) methodMap.get("playerService").invoke(obj, ioService);
            GameProcessorImpl gameProcessor = (GameProcessorImpl) methodMap.get("gameProcessor").invoke(obj, ioService, playerService, eP);
            appComponents.add(gameProcessor);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
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
        return getAppComponent((Class<C>) Class.forName(componentName));
    }
}
