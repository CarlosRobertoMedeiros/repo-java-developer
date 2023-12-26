package br.com.roberto.advancedtechniquesjava8.b.streams.d;

import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TerminalOperations {
    /*
    * Stream Terminal Operations
    * Method                                        Return Value                                  Reduction
    *
    *  count()                                      long                                          yes
    *  min(), max()                                 Optional<T> - Stream Maybe empty              yes
    *  findAny(), findFirst()                       Optional<T> -                                 no - May not look at all of the elements
    *  allMatch(), anyMatch(), noneMatch()          boolean                                       no - May not look at all of the elements
    *  forEach()                                    void                                          no - (as id does not return anything)
    *  reduce()                                     varies                                        Yes
    *  collect()                                    varies                                        Yes
    *
    * Reductions are a special type of terminal operation where ALL of the contents of the streams are combined
    * into a single primitive or Object e.g. long or Collection
    * * */

    public static void main(String[] args) {
        exampleCountMinAndMax();
        exampleFindAnyFindFirst();
        exampleAnyMatchallMatchnoneMatch();
        exampleForEach();
        exampleReduceMethod();
        exampleBinaryOperator();
        exampleByFunctionWithReduce();

    }

    public static void exampleCountMinAndMax(){
        //Optional<T> min (Comparator)
        //Optional<T> max (Comparator)
        /*
         * Optional introduce in Java 8 to replace "null". If the stream is empty then the Optional
         * will be empty (and we won't have to deal with null)
         * */
        System.out.println("Example Count Min and Max");
        long animalsCount = Stream.of("dog","cat")
                .count();
        System.out.println(animalsCount);

        Optional<String> min = Stream.of("deer","horse","pig")
                .min((s1,s2) -> s1.length() - s2.length());
        min.ifPresent(System.out::println); //pig

        Optional<Integer> max = Stream.of(4,6,2,12,9)
                .max((i1,i2)-> i1-i2);
        max.ifPresent(System.out::println); //12
    }

    public static void exampleFindAnyFindFirst(){
        /*
        * Optional<T> findAny()
        * Optional<T> findFirst()
        *
        * these are terminal operations but not reductions
        * as they sometimes return without processing all
        * the elements in the stream. Reductions reduce the
        * entire stream into no value
        * */
        System.out.println("Example using FindAny and FindFirst");

        Optional<String> any = Stream.of("John","Paul")
                .findAny();
        any.ifPresent(System.out::println); //John (usually)

        Optional<String> first = Stream.of("John","Paul")
                .findFirst();
        first.ifPresent(System.out::println); //John
    }

    public static void exampleAnyMatchallMatchnoneMatch(){
        //boolean anyMatch(Predicate)
        //boolean allMatch(Predicate)
        //boolean noneMatch(Predicate)
        System.out.println("Example using AnyMatch() AllMatch() NoneMatch()");

        List<String> names = Arrays.asList("Alan","Brian","Colin");
        Predicate<String> pred = name -> name.startsWith("A");
        System.out.println(names.stream().anyMatch(pred));  //true (one does)
        System.out.println(names.stream().allMatch(pred));  //false (two don´t)
        System.out.println(names.stream().noneMatch(pred)); //false (one does)
    }

    public static void exampleForEach(){
        // void forEach(Consumer)
        // As there is no return value, forEach() is no a reduction.
        // As thre return type is 'void', if you wanto something to
        // happen, it has to happen inside the Consumer (side-effect)
        System.out.println("Example using forEach()");
        Stream<String> names = Stream.of("Cathy","Pauline","Zoe");
        names.forEach(System.out::println);

        // Notes: forEach is also a method in the Collection interface.
        //        Streams cannot be the source of a for-each loop
        //        because streams do not implement the Iterable interface.
        //See example bellow for more details

        /*Stream<Integer> s = Stream.of(1);
        for (Integer i: s){} //error:required array of Iterable*/
    }

    public static void exampleReduceMethod(){
        /* The reduce() method combines a stream into a single object.
        *  It is a reduction, which means it processes all elements.
        *  The most common way of doing a reduction is to start with
        * an initial value and keep merging it with the next value
        *
        *  T reduce (T identity, BinaryOperator<T> acummulator)
        *       BinaryOperator<T> functional Method
        *           T apply(T t, T t)
        *
        * The identity is the initial value of the reduction and also
        * what is returned if the stream is empty. This means that there
        * will always be a result and thus Optional is not the return type
        * (on this version of reduce())
        *
        * The accumulator combines the current result with the
        * current value in the stream
        **/
        System.out.println("Example using reduce() Method");
        String name = Stream.of("s","e","a","n")
                .reduce("",(s,c) -> s+c);
        System.out.println(name); //sean

        Integer product = Stream.of(2,3,4)
                .reduce(1, (a,b) -> a*b);
        System.out.println(product); //24
    }

    public static void exampleBinaryOperator(){
        /*
        * Optional<T> reduce(BinaryOperator<T> accumulator)
        * When you leave out the indentity, an Optional is
        * returned because there may not be any data (all the
        * elements could have been filtered out earlier) There are
        * 3 possible results:
        *    a) empty stream => empty Optional returned
        *    b) one element in a stream => that element is returned
        *    c) multiple elements in stream => accumulator is applied
        */
        BinaryOperator<Integer> op = (a,b) -> a+b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(6);
        Stream<Integer> multiplyElements = Stream.of(3,4,5);
        empty.reduce(op).ifPresent(System.out::println);               //
        oneElement.reduce(op).ifPresent(System.out::println);          //  6
        multiplyElements.reduce(op).ifPresent(System.out::println);    // 12
        // Why not just require the identity and remove this method?
        // Sometimes it is nice to know if the sreatm is empty as opposed
        // to the case where there is a value returned from the accumulator
        // that happens to match the identity (however unlikely)
        Integer val = Stream.of(1,1,1)
                .reduce(1, (a,b) -> a);
        System.out.println(val);
    }

    public static void exampleByFunctionWithReduce(){
        // <U> reduce (U identity,
        //             BiFunction accumulator,
        //             BinaryOperator combiner)
        // We use this version when we are dealing with different types,
        // allowing us to create intermediate reductions and then combine
        // them at the end. This is useful when working with parallel stream -
        // The stream can be decomposed and reassembled by separate threads. For
        // example, if we wanted to count() the length of four 1000-character strings,
        // the first 2 values ant the last two values could be calculated independently.
        // The terminal results (2000) would then be combine into a final value (4000).
        // Example: We want to count the number of characters in each String
        System.out.println("Exemple using Function With Reduce");

        Stream<String> stream = Stream.of("car","bus","train","aeroplane");
        int length = stream.reduce(0, //identity
                                  ( n,str ) -> n+str.length(), //n is Integer
                                  ( n1,n2 ) -> n1+n2); // both are Integers
        System.out.println(length); //20


    }

}
