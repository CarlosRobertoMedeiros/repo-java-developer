package br.com.roberto.advancedtechniquesjava8.b.streams.e;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStreamsExample {
    public static void main(String[] args) {
        //Using Collection<E>
        Stream<String> animalsStream = List.of("sheep","pigs","horses")
                .parallelStream();

        //Using Stream<T>
        Stream<String> animalsStream2 = Stream.of("sheep","pigs","horses")
                .parallel();

        System.out.println("Example Using sum()");
        int sum = Stream.of(10,20,30,40,50,60)
                // IntStream has the sum() method so we use
                // the mapToInt() method to map from Stream<Integer>
                // to an IntStream(i.e. a stream of int primitives).
                // IntStream mapToInt(ToIntFunction)
                //     ToIntFunction is a functional interface:
                //         int applyAsInt(T value)
                .mapToInt(n -> n.intValue())
                //   .mapToInt(Integer::intValue)
                //   .mapToInt(n -> n)
                .sum();
        System.out.println("Sum == "+sum); //210

        System.out.println("Example Using parallel() and sequentialStream()");
        /*
        * Be careful if order is important, as the order of thread completion will
        * determine that final result (not the order in original collection).
        */
        sequentialStreamExample();
        parallelStreamExample();
    }

    public static void sequentialStreamExample(){
        System.out.println("Sequential Stream doesn't problem with sequential");
        Arrays.asList("a","b","c")
                .stream()
                .forEach(System.out::print); //abc
    }

    public static void parallelStreamExample(){
        System.out.println("Parallel Stream Be Carefull with Order");
        Arrays.asList("a","b","c")
                .stream()
                .parallel()
                .forEach(System.out::print); //bca
    }
}


