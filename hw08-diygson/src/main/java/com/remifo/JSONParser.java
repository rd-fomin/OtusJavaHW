package com.remifo;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

public class JSONParser {

    public static String toJson(Object src) {
        if ( src == null )
            return toJson( JsonNull.INSTANCE );
        return toJson( src, src.getClass() );
    }

    public static String toJson(JsonNull jsonNull) {
        return "null";
    }

    private static String toJson(Object src, Type typeOfSrc) throws JSONParseException {
        var stringBuilder = new StringBuilder();
        toJson(src, typeOfSrc, stringBuilder);
        return stringBuilder.toString();
    }

    private static void toJson(Object src, Type typeOfSrc, StringBuilder stringBuilder) throws JSONParseException {
        Field[] fields = src.getClass().getDeclaredFields();
        if (src instanceof Number) {
            stringBuilder.append(src);
        } else if (src instanceof String) {
            stringBuilder.append('"').append(src).append('"');
        } else {
            stringBuilder.append('{');
            for (Field field : fields) {
                stringBuilder.append('"').append(field.getName()).append("\":");
                toJson(src, stringBuilder, field);
            }
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(",")).append('}');
        }
    }

    private static void toJson(Object src, StringBuilder stringBuilder, Field field) throws JSONParseException {
        field.setAccessible(true);
        try {
            stringBuilder.append(toJson(src, field, field.getType()));
        } catch (IllegalAccessException e) {
            throw new JSONParseException(e);
        }
        stringBuilder.append(',');
    }

    private static String toJson(Object src, Field field, Type type) throws IllegalAccessException {
        if (field.get(src) == null) {
            return toJson(JsonNull.INSTANCE);
        } else if (type.equals(String.class)) {
            return '"' + field.get(src).toString() + '"';
        } else if (type.equals(List.class)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('[');
            Iterator<?> iterator = ((List) field.get(src)).iterator();
            while (iterator.hasNext()) {
                Object o = iterator.next();
                if (o != null)
                    toJson(o, o.getClass(), stringBuilder);
                else
                    stringBuilder.append(toJson(JsonNull.INSTANCE));
                stringBuilder.append(',');
            }
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(",")).append(']');
            return stringBuilder.toString();
        } else {
            return field.get(src).toString();
        }
    }

}
