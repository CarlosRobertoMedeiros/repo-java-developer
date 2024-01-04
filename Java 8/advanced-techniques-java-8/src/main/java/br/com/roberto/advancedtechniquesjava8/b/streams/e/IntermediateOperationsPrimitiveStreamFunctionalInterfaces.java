package br.com.roberto.advancedtechniquesjava8.b.streams.e;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class IntermediateOperationsPrimitiveStreamFunctionalInterfaces {
    /**
     * Functional Interfaces for primitive Streams
     *
     * Veja o arquivo no diretorio:
     *      advanced-techniques-java-8\src\images\00-Interfaces Funcionais - StreamApi vs PrimitiveStream.jpg
     *
     **/
    public static void main(String[] args) {
        creatingPrimitiveStreams();
        primitiveStreamsExampleMinMaxSumAverage();
        commonPrimitiveStreamsMethods();
        mappingObjectStreams();
        mappingPrimitiveStreams();

    }

    public static void creatingPrimitiveStreams(){
        System.out.println("Example Creating Primitive Streams");
        int[] ia = {1,2,3};
        double[] da = {1.1,2.2,3.3};
        long[] la = {1L,2L,3L};

        IntStream intStream = Arrays.stream(ia);
        DoubleStream doubleStream = Arrays.stream(da);
        LongStream longStream = Arrays.stream(la);
        System.out.println(intStream.count()+", "+doubleStream.count()+", "+ longStream.count()); //3, 3, 3

        IntStream intStream2 = IntStream.of(1,2,3);
        DoubleStream doubleStream2 = DoubleStream.of(1.1,2.2,3.3);
        LongStream longStream2 = LongStream.of(1L,2L,3L);
        System.out.println(intStream2.count()+", "+doubleStream2.count()+", "+ longStream2.count()); //3, 3, 3
    }

    public static void primitiveStreamsExampleMinMaxSumAverage(){
        /**
         * The primitive streams, in addition to containing many
         * of Streams methods, also contain specialised methods
         * for working with numeric data.
         *
         * The primitive streams know how to perform certain
         * common operations automatically e.g. min(), max(), sum() and average()
         */
        System.out.println("Example Primitive Streams Using min() max() sun() and average()");

        //1. Using Stream<T> and reduce (identity, accumulator)
        Stream<Integer> numbers = Stream.of(1,2,3);
        // Integer reduce(Integer identity, BinaryOperator accumulator)
        //    BinaryOperator extends BiFunction<T,T,T>
        //        T apply(T,T)
        // starting the accumulator with 0
        //  n1 + n2
        // 0 + 1 == 1 (n1 now becomes 1)
        // 1 + 2 == 3 (n1 now becomes 3)
        // 3 + 3 == 6
        System.out.println(numbers.reduce(0,(n1,n2) -> n1+n2)); //6

        // 2. Using IntStream and sum()
        //  IntStream mapToInt(ToIntFunction)
        //    ToIntFunction is a functional interface:
        //      int applyAsInt(T value)
        IntStream intS = Stream.of(1,2,3)
                .mapToInt(n -> n); //unboxed
        int total = intS.sum();
        System.out.println(total); //6
    }

    public static void commonPrimitiveStreamsMethods(){
        /**
         *       Common Primitive Stream Method
         *
         * Method                           PrimitiveStream
         *
         * OptionalDouble   average()       IntStream
         *                                  LongStream
         *                                  DoubleStream
         *
         * OptionalInt      max()           IntStream
         * OptionalLong     max()           LongStream
         * OptionalInt      max()           DoubleStream
         *
         * OptionalInt      min()           IntStream
         * OptionalLong     min()           LongStream
         * OptionalInt      min()           DoubleStream
         *
         * int              sum()           IntStream
         * long             sum()           LongStream
         * double           sum()           DoubleStream
         *
         *
         * */
        System.out.println("Example Using Common Primitive Stream Methods max()");
        OptionalInt max = IntStream.of(10,20,30)
                .max(); //Terminal Operation
        max.ifPresent(System.out::println); //30

        System.out.println("Example Using Common Primitive Stream Methods min()");
        OptionalDouble min = DoubleStream.of(10.0,20.0,30.0)
                .min(); //Terminal Operation
        //NoSuchElementException is thrown if no value present
        System.out.println(min.orElseThrow());//10

        System.out.println("Example Using Common Primitive Stream Methods average()");
        OptionalDouble average = LongStream.of(10L,20L,30L)
                .average(); //Terminal Operation
        System.out.println(average.orElseGet(() -> Math.random())); //20
    }

    public static void mappingObjectStreams(){
        /*
        * Veja o arquivo no diretorio:
        * advanced-techniques-java-8\src\images\01-MappingStreams.JPG
        * */
        //Stream<T> to Stream<T>
        System.out.println("Example Using Map");
        Stream.of("ash","beech","sycamore")
                // map(Function)
                //     Function<T,R> => Function<String,String>
                //        String apply(String s)
                .map(tree -> tree.toUpperCase())
                .forEach(System.out::println);//ASH,BEECH,SYCAMORE

        System.out.println("Example Using MapToDouble");
        //Stream<T> to DoubleStream
        DoubleStream doubleStream = Stream.of("ash","beech","sycamore")
                // DoubleStream mapToDouble(ToDoubleFunction mapper);
                //    double applyAsDouble(T value);
                .mapToDouble(tree -> tree.length()); //upcast in backgroud
        doubleStream.forEach(System.out::println);// 3.0, 5.0, 8.0

        System.out.println("Example Using MapToInt");
        //Stream<T> to IntStream
        IntStream intStream = Stream.of("ash","beech","sycamore")
                // IntStream mapToInt(ToIntFunction<? super T> mapper);
                //       int applyAsInt(T value) => int applyAsInt(String value)
                .mapToInt(tree -> tree.length());
        intStream.forEach(System.out::println);// 3, 5, 8)


        System.out.println("Example Using MapToLong");
        //Stream<T> to LongStream
        LongStream longStream = Stream.of("ash","beech","sycamore")
                // LongStream mapToLong(ToLongFunction<? super T> mapper);
                //       int applyAsInt(T value) => int applyAsInt(String value)
                //            long applyAsLong(T value);
                .mapToLong(tree -> tree.length());
        longStream.forEach(System.out::println);// 3, 5, 8)
    }

    public static void mappingPrimitiveStreams(){

        System.out.println("Example Using MapToObject");
        // IntStream to Stream<T>
        Stream<String> streamAges = IntStream.of(1,2,3)
                // <U> Stream<U> mapToObj(IntFunction<? extends U> mapper);
                //     R apply(int value);
                .mapToObj(n -> "Number: "+n);
                // void forEach(Consumer<? super T> action);
                //      void accept(T t);
        streamAges.forEach(System.out::println); //Number: 1,Number: 2,Number: 3

        System.out.println("Example Using MapToDouble");
        // IntStream to DoubleStream
        DoubleStream doubleStream = IntStream.of(1,2,3) //must open stream again as it is closed!
                .mapToDouble(n -> Double.valueOf(n)); //Cast Not necessary Unboxing Outboxing
        doubleStream.forEach(System.out::println); //1.0,2.0,3.0

        System.out.println("Example Using IntStream to IntStream");
        // IntStream to IntStream
        IntStream intStream = IntStream.of(1,2,3) //must open stream again as it is closed!
                .map(n -> n * 2); //Cast Not necessary Unboxing Outboxing
        intStream.forEach(System.out::println); //2,4,6

        System.out.println("Example Using IntStream to LongStream");
        // IntStream to LongStream
        IntStream.of(1,2,3) //must open stream again as it is closed!
                .mapToLong(n -> (long) n) //Cast Not necessary Unboxing Outboxing
                .forEach(System.out::println); //1,2,3





    }

}
