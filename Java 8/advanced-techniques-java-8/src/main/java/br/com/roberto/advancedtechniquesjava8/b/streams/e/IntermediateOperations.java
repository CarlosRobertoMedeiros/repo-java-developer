package br.com.roberto.advancedtechniquesjava8.b.streams.e;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
        ExampleMap();
        ExampleFlatMap();
        ExampleSorted();
        ExampleSortedDetailed();
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

    public static void ExampleMap(){
        /**
         * map() creates a one-to-one mapping between elements in
         * the stream and elements in the nex stage of stream.
         *
         * map() is for transforming data.
         *
         * <R> Stream<R> map(Function<T,R> mapper)
         *      Function's functional method: R apply(T t)
         * */
        System.out.println();
        System.out.println("Example Using Map");
        Stream.of("book","pen","ruler")
                .map(s -> s.length())
                .forEach(System.out::print); //435
    }

    public static void ExampleFlatMap(){
        /*
        * flatMap() take each element in the stream
        * e.g Stream<List<String>> and makes any elements it contains
        * top-level elements in a single stream e.g. Stream<String>
        */
        System.out.println("Example Using FlatMap");
        List<String> list1 = Arrays.asList("sean","desmond");
        List<String> list2 = Arrays.asList("mary","ann");
        Stream<List<String>> listStream = Stream.of(list1,list2);

        //flatMap(Function(T,R)) IN:T OUT:R
        //flatMap(List<String>, Stream<String>)
        listStream.flatMap(list -> list.stream())
                .forEach(System.out::print); //seandesmondmaryann
    }

    public static void ExampleSorted(){
        /*
        *  sorted() returns a stream with the elements sorted.
        *
        *  Just like sorting arrays, Java uses natural ordening unless we provide
        * a comparator
        *
        * sorted() is a statefull intemediate operation; it needs to see all of
        * the data before it can sort it
        */
        System.out.println("Example Using Sorted");
        Person john = new Person("John","23");
        Person mary = new Person("Mary","25");
        Stream.of(mary,john)
                //.sorted(Comparator.comparing(Person::getAge))
                .sorted(Comparator.comparing(p -> p.getAge()))
                .forEach(System.out::print); //Person{name='John', age='23'}Person{name='Mary', age='25'}
    }

    public static void ExampleSortedDetailed(){
        /**
         *  Stream<T> sorted()
         *  Stream<T> sorted(Comparator<T> comparator)
         *  Output: 0.Tim 1.Tim 0.Jim 1.Jim 0.Peter 0.Ann 1.Ann 0.Mary 2.Ann 3.Ann 2.Jim 3.Jim
         *
         * */
        System.out.println("Example Using Sorted Detailed");
        Stream.of("Tim","Jim","Peter","Ann","Mary")
                .peek(name -> System.out.print(" 0."+name)) //"Tim","Jim","Peter","Ann","Mary"
                .filter(name -> name.length() == 3)
                .peek(name -> System.out.print(" 1."+name)) //"Tim","Jim","Ann"
                .sorted() //"Tim","Jim","Ann" (Stored)
                .peek(name -> System.out.print(" 2."+name)) //Ann,Jim
                .limit(2)
                .forEach(name -> System.out.print(" 3."+name));//Ann,Jim
    }


}
class Person{
    private String name;
    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}