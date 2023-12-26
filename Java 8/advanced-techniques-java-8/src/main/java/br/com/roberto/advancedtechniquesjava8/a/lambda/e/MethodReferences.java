package br.com.roberto.advancedtechniquesjava8.a.lambda.e;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class MethodReferences {

    public static void main(String[] args) {
        methodReference();
        boundMethodReference();
        unboundMethodReference();
        staticMethodReference();
        constructorMethodReference();
    }

    public static void methodReference(){
        List<String> names = Arrays.asList("Sean","Mary","John");
        names.forEach(name -> System.out.println(name));
        names.forEach(System.out::println); //Method Reference
    }

    public static void boundMethodReference(){
        String name = "Mr. Joe Bloggs";
        //Supplier<T>
        // T get()
        Supplier<String> lowerL = () -> name.toLowerCase(); //Lambda
        Supplier<String> lowerLMR = name::toLowerCase; //Method Lambda

        // No need to say which instance to call it on - the supplier is bound(vinculado) to name
        System.out.println(lowerL.get());
        System.out.println(lowerLMR.get());


        //Predicate<T>
        // boolean test(T t)
        // Even though startsWith is overloaded, boolean startsWith(String) and
        // boolean startsWith(String, int), because we are creating a Predicate which
        // has a functional method of test(T t), the startsWith(String) is used.
        // This is where "context" is important.
        Predicate<String> titleL = (title) -> name.startsWith(title);
        Predicate<String> titleMR = name::startsWith;

        System.out.println(titleL.test("Mr."));
        System.out.println(titleMR.test("Ms."));
    }

    public static void unboundMethodReference(){
        //Function<T,R>
        // R apply(T t)
        //  String apply (String)
        Function<String, String> upperL = s -> s.toUpperCase();
        Function<String, String> upperLMr = String::toUpperCase;
        // the function is unbound, so you need specify which instance to call it on
        System.out.println(upperL.apply("sean"));
        System.out.println(upperLMr.apply("sean"));

        //BiFunction<T,U,R>
        // R apply(T t, U u)
        //  String apply (String, String)
        BiFunction<String, String, String> concatL = (s1, s2) -> s1.concat(s2);
        BiFunction<String, String, String> concatMr = String::concat;
        System.out.println(concatL.apply("Sean","Keneddy"));

        //1st parameter is used for executing the instance method
        // "Sean ".concat("Kennedy)
        System.out.println(concatMr.apply("Sean","Keneddy"));
    }

    public static void staticMethodReference(){
        // Static method reference are considered UNBOUND also. An example static method
        // is Collections.sort(List)
        // Consumer<T>
        //   void accept(T t)
        //      void accept(List<Integer>)
        //  NB: Consumer takes one parameter => sort(List) is used, as oppossed to sort(List, Comparator)
        Consumer<List<Integer>> sortL = list -> Collections.sort(list);
        Consumer<List<Integer>> sortMR = Collections::sort;

        List<Integer> listOfNumbers = Arrays.asList(2,1,5,4,9);
        sortL.accept(listOfNumbers);
        System.out.println(listOfNumbers);

        listOfNumbers = Arrays.asList(8,12,4,3,7);
        sortMR.accept(listOfNumbers);
        System.out.println(listOfNumbers);
    }

    public static void constructorMethodReference(){
        // Supplier<T>
        //    T  get()
        Supplier<StringBuilder> sbL = () -> new StringBuilder(); //lambda
        Supplier<StringBuilder> sbMr = StringBuilder::new; //Method Reference

        // Function<T,R>
        //  R apply(T)
        //    List<String> apply(Integer)
        //  ArrayList(int initialCapacity)
        Function<Integer,List<String>> alL = x -> new ArrayList<>(x);
        Function<Integer,List<String>> alMr = ArrayList::new;
        List<String> ls1 = alL.apply(10);
        ls1.add("21");
        System.out.println(ls1);
        List<String> ls2 = alMr.apply(5);
        ls2.add("88");
        System.out.println(ls2);
    }
}
