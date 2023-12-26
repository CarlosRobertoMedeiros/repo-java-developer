package br.com.roberto.advancedtechniquesjava8.b.streams.b;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamLaziness {
    public static void main(String[] args) {
        streamLazinessExample1();
        streamLazinessExample2();
        streamLazinessExample3();
    }

    public static void streamLazinessExample1(){
        System.out.println("Stream Example 1");
        Stream.of("Alex","David","April","Edward")
                .filter(s -> {
                    System.out.println("filter: "+s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach"+s));
    }

    public static void streamLazinessExample2(){
        /*
        * This cap help in reducing the actual number of operations - instead of
        * mapping "Alex","David","April","Edward" and then anyMatch() on
        * "Alex" (5 operations in total), we process the elements vertically resulting in
        * only 2 operations. While this is a small example, it shows the beneficits to be
        * had if we had millions of data elements to be processed
        *   map: Alex
        *   anyMatch: ALEX
        * */
        System.out.println("Stream Example 2");
        Stream.of("Alex","David","April","Edward")
               .map(s -> {
                    System.out.println("map: "+s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " +s);
                    return s.startsWith("Alex");
                });

    }

    public static void streamLazinessExample3(){
        /*
        * Execution
        *
        * April              - peek
        * filter1: April     - filter1 removes April
        * Ben                - peek
        * filter1: Ben       - filter1 passed Ben on
        * filter2: Ben       - filter1 removes Ben
        * Charlie            - peek
        * filter1: Charlie   - filter1 passes Charlie on
        * filter2: Charlie   - filter1 passes Charlie on
        * Charlie            - forEach()
        *
        * Note: limit(1) means David, Benildus or Christian are not processed at all i.e nome
        *       of them appear in the output via "peek()"
        * * */
        System.out.println("Stream Example 3");
        List<String> names = Arrays.asList("April","Ben", "Charlie",
                "David","Benildus","Christian");
        names.stream()
                .peek(System.out::println)
                .filter(s -> {
                    System.out.println("Filter 1:"+s);
                    return s.startsWith("B") || s.startsWith("C");
                })
                .filter(s -> {
                    System.out.println("Filter 2:"+s);
                    return s.length() > 3;
                })
                .limit(1)
                .forEach(System.out::println);


    }
}
