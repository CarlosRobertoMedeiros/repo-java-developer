package br.com.roberto.advancedtechniquesjava8.b.streams.d;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperationsCollectors {

    public static void main(String[] args) {
          exampleCollectorUsingListWithGroupBy();
          exampleCollectorUsingSetWithGroupBy();
          exampleCollectorUsingListWithGroupByReturningTreeMap();
          exampleCollectorUsingPartitioningBy();
          exampleCollectorUsingPartitioningByUsingTheLengthName();
          exampleCollectorUsingPartitioningByUsingTheLengthNameForcingEmptyList();
          exampleCollectorUsingPartitioningByWithSet();
    }

    public static void exampleCollectorUsingListWithGroupBy(){
        System.out.println("Example Collectors Using List With Group By");
        Stream<String> names = Stream.of("Joe","Tom","Tom","Alan","Peter");
        Map<Integer, List<String>> map =
            names.collect(
                    // passing in a Function that determines the key in the map
                    Collectors.groupingBy(String::length)); // s -> s.length()
        System.out.println(map); //{3=[Joe, Tom, Tom], 4=[Alan], 5=[Peter]}
    }

    public static void exampleCollectorUsingSetWithGroupBy(){
        System.out.println("Example Collectors Using Set With Group By");
        Stream<String> names = Stream.of("Joe","Tom","Tom","Alan","Peter");

        Map<Integer, Set<String>> map =
                names.collect(
                        Collectors.groupingBy(
                                String::length,       // Key Value
                                Collectors.toSet()    // What to do with the value
                        )
                );
        System.out.println(map); //{3=[Joe, Tom], 4=[Alan], 5=[Peter]}
    }

    public static void exampleCollectorUsingListWithGroupByReturningTreeMap(){
        /**
         * There are no garantees on the type of Map returned
         *
         * What if we wanted to ensure we got back a TreeMap bue leave the value
         *  as a List? We can achieve this by using the (optional) map type Supplier
         *  while passing down the toList() collector.
         *
         * */
        System.out.println("Example Collectors Using List With Group By Returing TreeMap");
        Stream<String> names = Stream.of("Joe","Tom","Tom","Alan","Peter");

        Map<Integer, List<String>> map =
                names.collect(
                        Collectors.groupingBy(
                                String::length,       // Key Value
                                TreeMap::new,         // Map type Supplier
                                Collectors.toList()   // Downstream Collector
                        )
                );
        System.out.println(map); //{3=[Joe, Tom, Tom], 4=[Alan], 5=[Peter]}
        System.out.println(map.getClass()); //class java.util.TreeMap
    }

    public static void exampleCollectorUsingPartitioningBy(){
        /**
         * Partitioning is a special case of grouping where there are
         * only two possible groups - true and false.
         *
         * The keys will be the booleans true and false
         * */
        System.out.println("Example Collectors Using PartitioningBy");
        Stream<String> names = Stream.of("Thomas","Teresa","Mike","Alan","Peter");
        Map<Boolean, List<String>> map =
                names.collect(
                        Collectors.partitioningBy(s -> s.startsWith("T"))
                );
        System.out.println(map); // {false=[Mike, Alan, Peter], true=[Thomas, Teresa]}
    }

    public static void exampleCollectorUsingPartitioningByUsingTheLengthName(){
        System.out.println("Example Collectors Using PartitioningBy with the Length name");
        Stream<String> names = Stream.of("Thomas","Teresa","Mike","Alan","Peter");

        Map<Boolean, List<String>> map =
                names.collect(
                    Collectors.partitioningBy(s -> s.length() > 4)
                );
        System.out.println(map); // {false=[Mike, Alan], true=[Thomas, Teresa, Peter]}
    }

    public static void exampleCollectorUsingPartitioningByUsingTheLengthNameForcingEmptyList(){
        System.out.println("Example Collectors Using PartitioningBy with the length name forcing empty");
        Stream<String> names = Stream.of("Thomas","Teresa","Mike","Alan","Peter");

        Map<Boolean, List<String>> map =
                names.collect(
                        Collectors.partitioningBy(s -> s.length() > 10)
                );
        System.out.println(map); // {false=[Thomas, Teresa, Mike, Alan, Peter], true=[]} TRU IS EMPTY LIST
    }

    public static void exampleCollectorUsingPartitioningByWithSet(){
        System.out.println("Example Collectors Using PartitioningBy with Set");
        Stream<String> names = Stream.of("Alan","Teresa","Mike","Alan","Peter");

        Map<Boolean, Set<String>> map =
                names.collect(Collectors.partitioningBy(
                        s -> s.length() > 4,
                        Collectors.toSet()
                ));
        System.out.println(map);//{false=[Mike, Alan], true=[Teresa, Peter]}



    }

}
