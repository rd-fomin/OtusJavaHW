package com.remifo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

public class MyTest {
    static <T> T createMyClass(T object) {
        InvocationHandler handler = new LogInvocationHandler<>(object);
        return (T) Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                handler
        );
    }

    static class LogInvocationHandler<T> implements InvocationHandler {
        private T object;
        private Set<Method> methods = new HashSet<>();

        public LogInvocationHandler(T object) {
            this.object = object;
            for ( Method method : object.getClass().getDeclaredMethods() )
                if ( method.isAnnotationPresent( Log.class ) ) {
                    try {
                        for ( Class<?> clazz : object.getClass().getInterfaces() )
                            methods.add(clazz.getDeclaredMethod(method.getName(), method.getParameterTypes()));
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ( this.methods.contains( method ) )
                printString(method.getName(), args != null && args.length > 0 ? getString(args) : "none");
            return method.invoke( object, args );
        }

        private String getString(Object... args) {
            if ( args.length > 1 ) {
                StringBuilder stringBuilder = new StringBuilder();
                for ( int i = 0; i < args.length; i++ ) {
                    stringBuilder.append( "param-" ).append( i + 1 ).append( ": " ).append( args[i] );
                    if ( i != args.length - 1 ) {
                        stringBuilder.append( ", " );
                    }
                }
                return stringBuilder.toString();
            } else {
                return args[0].toString();
            }
        }

        private void printString(String methodName, String params) {
            System.out.printf( "executed method: %s, %s\n", methodName, params );
        }

    }
}
