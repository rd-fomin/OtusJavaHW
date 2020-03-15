package com.remifo;

import com.remifo.annotations.After;
import com.remifo.annotations.Before;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestFramework {

    public TestCount startTest(String className ) {

        Method beforeMethod = null;
        Method afterMethod = null;
        Set<Method> testMethods = new HashSet<>();

        Method[] methods = getMethods( className );

        for ( Method method : methods ) {
            method.setAccessible( true );
            if ( method.isAnnotationPresent( Before.class ) )
                beforeMethod = method;
            else if ( method.isAnnotationPresent( After.class ) ) {
                afterMethod = method;
            } else {
                testMethods.add( method );
            }
        }

        TestCount testCount = new TestCount();

        for ( Method method : testMethods ) {
            Object object = createObject(className);
            try {
                if (beforeMethod != null) {
                    beforeMethod.invoke(object);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            try {
                method.invoke(object);
                System.out.println(method.getName());
                testCount.plusNumberSuccessfulTest();
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getCause().getMessage());
                testCount.plusNumberUnsuccessfulTest();
            }
            try {
                if (afterMethod != null) {
                    afterMethod.invoke(object);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return testCount;

    }

    private Method[] getMethods( String className ) {
        Method[] methods = new Method[0];
        try {
            methods = Class.forName( className ).getDeclaredMethods();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return methods;
    }

    private Object createObject( String className ) {
        Object constructor = null;
        try {
             constructor = Class.forName( className ).getConstructor().newInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return constructor;
    }

}
