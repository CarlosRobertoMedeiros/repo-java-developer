package br.com.roberto.advancedtechniquesjava8.lambda.c;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.*;

public class FunctionalInterface_from_API {

    public static void main(String[] args) {
        FunctionalInterface_from_API fiApi = new FunctionalInterface_from_API();
        fiApi.predicate();
        fiApi.supplier();
        fiApi.consumer();
        fiApi.function();
        fiApi.unaryOperation();

    }

    public void predicate(){
        // Predicate<T> is a functional interface i.e one abstract method
        //  boolean test(T t);
        Predicate<String> pStr = s -> s.contains("City");
        System.out.println(pStr.test("Paris City"));

        // BiPredicate<T> is a functional interface i.e one abstract method
        //  boolean test(T t, U u);
        BiPredicate<String, Integer> checkLength = (str,len) -> str.length()==len;
        System.out.println(checkLength.test("Paris City",8));
    }

    public void supplier(){
        Supplier<StringBuilder> supSB = () -> new StringBuilder();
        System.out.println("Supplier SB: " + supSB.get().append("SK"));

        Supplier<LocalTime> supTime = () -> LocalTime.now();
        System.out.println("Supplier Time: " + supTime.get());

        Supplier<Double> sRandom = () -> Math.random();
        System.out.println("SRandom Get: " + sRandom.get());
    }

    public void consumer(){
        // Consumer<T> is a functional interface i.e one abstract method
        //  void accept(T t);
        Consumer<String> cStr = s -> System.out.println(s);
        cStr.accept("Hello World");

        List<String> names = Arrays.asList("John","Mary");
        names.forEach(cStr);

        //BiConsumer<T, U> is a functional interface i.e one abstract method
        //  void accept(T t, U u);
        var mapCaitalCities = new HashMap<String,String>();
        // Note: The return value of put(k,v) is just ignored (and not returned from the lambda)
        BiConsumer<String,String> biConsumer = (key, value) -> mapCaitalCities.put(key, value);
        biConsumer.accept("Dublin","Ireland");
        biConsumer.accept("Washington D.C.","USA");
        System.out.println(mapCaitalCities);

        BiConsumer<String,String> mapPrint = (key, value) -> System.out.println(key+" is the capital of: "+value);
        mapCaitalCities.forEach(mapPrint);
    }

    public void function(){
        // Function<T, R> is a functional interface i.e one abstract method
        //  R apply(T t);
        Function<String, Integer> fn1 = s -> s.length();
        System.out.println("Function: "+fn1.apply("Moscow"));

        //BiFunction<T,U,R> is a functional interface i.e one abstract method
        //  R apply(T t, U u);
        BiFunction<String, String, Integer> bfn = (s1, s2) -> s1.length() + s2.length();
        System.out.println("BiFunction: "+bfn.apply("Willian","Sheakspeare"));

        BiFunction<String, String, String> bfn2 = (s1, s2) -> s1.concat(s2);
        System.out.println("BiFunction: "+bfn2.apply("Willian","Sheakspeare"));
    }

    public void unaryOperation(){
        //UnaryOperator<T> extends Function<T,T> is a functional interface i.e one abstract method
        //  T apply(T t);
        UnaryOperator<String> unaryOp = name -> "My name is "+name;
        System.out.println("UnaryOperator: "+unaryOp.apply("Carlos"));

        //BinaryOperator<T> extends BiFunction<T,T,T> is a functional interface i.e one abstract method
        //  T apply(T t, T u);
        BinaryOperator<String> binaryOp = (name1, name2) -> name1.concat(name2);
        System.out.println("BinaryOperator: "+binaryOp.apply("Carlos","Roberto"));
    }



}
