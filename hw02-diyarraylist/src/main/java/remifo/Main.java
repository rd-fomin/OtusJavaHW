package remifo;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        DIYArrayList<String> strings = new DIYArrayList<>();
        strings.add("Hello");
        strings.add("Hall");
        strings.add("Go");
        strings.add("Reach");
        strings.add("End");
        strings.add(3, "Мир");
        strings.add(3, "Наука");
        strings.add(3, "Торговля");
        strings.add(8, "Мысль");
        strings.add("бежит");
        strings.add("Коля");
        strings.add("движет");
        strings.add("обгоняет");
        strings.add("несет");
        strings.add("ведет");
        Main.printArray(strings);

//        strings.remove("Мысль");
//        Main.printArray(strings);
//
//        strings.remove(3);
//        Main.printArray(strings);
//
//        printArray(strings);
//
//        strings.remove(2);
//        printArray(strings);
//
//        strings.add(2, "Went");
//        printArray(strings);
//
//        strings.add("Hall");
//        strings.add("Go");
//        printArray(strings);

        strings.add(null);
        System.out.println(strings.contains(null));
        System.out.println();
        printArray(strings);

        System.out.println(strings.indexOf("Hall"));
        System.out.println(strings.lastIndexOf("Hall"));

        DIYArrayList<String> strings1 = new DIYArrayList<>();
        strings1.add("asdadbsdfbs");
        strings1.add("sdlmxkbdjg");
        strings1.add("asflkhgda");
        strings1.add("adfngsqpoda");
        System.out.println(strings1.size());
        printArray(strings1);

        strings.addAll(3, strings1);
        printArray(strings);

        Collections.addAll(strings, strings1.get(0), strings1.get(1));
        printArray(strings);

        Collections.copy(strings, strings1);
        printArray(strings);

        Collections.sort(strings, String::compareToIgnoreCase);
        printArray(strings);

//        String[] strings1 = new String[5];
//        strings.toArray(strings1);
//        System.out.println(strings1[2]);
    }

    private static <T> void printArray(DIYArrayList<T> diyArrayList) {
        for (T o : diyArrayList) {
            System.out.println(o);
        }
        System.out.println();
    }

}
