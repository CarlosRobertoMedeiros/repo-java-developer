package br.com.roberto.advancedtechniquesjava8.b.streams.e;

import java.util.stream.Stream;

public class IntermediateOperations {

/*
*   Unlike a terminal operation, an intermediate operation produces
* a stream as a result
*
*   Stream<T> filter (Predicate predicate)
*   The filter() method returns a stream in the elements that
*   MATCH the given predicate.
*/
    public static void main(String[] args) {
        ExampleFilter();
        ExampleDistinct();
        ExampleLimit();
        ExampleMapflatMapSorted();
    }

    public static void ExampleFilter(){
        /*
         *   Unlike a terminal operation, an intermediate operation produces
         * a stream as a result
         *
         *   Stream<T> filter (Predicate predicate)
         *   The filter() method returns a stream in the elements that
         *   MATCH the given predicate.
         */
        System.out.println("Example Using Filter");
        Stream.of("galway","mayo","roscommon")
                .filter(contryName -> contryName.length() > 5)
                .forEach(System.out::println); //galwayroscommon
    }

    public static void ExampleDistinct(){

        /*
        * distinct() returns a stream with duplicate values removed.
        *   equals() is used i.e. case sensitive
        *
        * distinct() is statefull intermediate operation
        *   It behaves like a filter - if it has no seen the object previously, it
        *   passes it on and remembers it. if has seen it already, if filters is out.
        *
        *  Stream<T> distinct()
        *   distinct() os a statefull intermediate operation
        *   Output: 1. eagle 2. eagle 1.eagle 1.EAGLE 2.EAGLE
        *
        * Caso tenha feito antes o valor do distinct nas primeiras linhas
        * ele apenas filtra para as próximas
        *
        */
        System.out.println("Example Using Distinct");
        Stream.of("eagle","eagle","EAGLE")
                .peek(s -> System.out.print(" 1."+s))
                .distinct()
                .forEach(s -> System.out.println(" 2."+s));
    }

    public static void ExampleLimit(){
        /*
        * limit() is a short-circuiting stateful method intermediate operation.
        *
        * Stream<T> limit(long maxSize)
        * limit is a short-circuiting stateful method intermediate operation.
        * Lazy evaluation - 66, 77, 88 and 99
        * are not streamed as they are not needed (limit of 2 i.e. 44 and 55)
        *
        * Output:
        *   A - 11 A - 22 A - 33 A - 44 B - 44 C - 44 A - 55 B - 55 C -55
        *
        */
        System.out.println("Example Using Limit");
        Stream.of(11,22,33,44,55,66,77,88,99)
                .peek(n -> System.out.print(" A - "+n))
                .filter(n -> n > 40 )
                .peek(n -> System.out.print(" B - "+n))
                .limit(2)
                .forEach(n -> System.out.print(" C - "+n));
    }

    public static void ExampleMapflatMapSorted(){

    }
}
