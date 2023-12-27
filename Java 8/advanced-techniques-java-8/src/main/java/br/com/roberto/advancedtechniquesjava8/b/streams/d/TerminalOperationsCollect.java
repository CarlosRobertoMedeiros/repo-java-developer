package br.com.roberto.advancedtechniquesjava8.b.streams.d;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperationsCollect {
    public static void main(String[] args) {
       exampleStringBuilderCollect();
       exampleCollectorsWithJoiningAndAverage();
       exampleCollectionIntoMaps();
       example2CollectionIntoMaps();
       example3CollectionIntoMapsUsingHashMap();
    }

    public static void exampleStringBuilderCollect(){
        // StringBuilder collect (Supplier<StringBuilder> supplier,
        //               BiConsumer<StringBuilder,String> accumulator,
        //               BiConsumer<StringBuilder,StringBuilder> combiner)
        // This version is used when you want complete control over
        // how collecting should work. The accumulator adds an element
        // to the collection e.g. the next String to StringBuilder.
        // The combiner takes two collections and merges them. It is useful
        // in parallel processing.
        System.out.println("Example Using StringBuilder");
        StringBuilder word = Stream.of("ad","jud","i","cate")
                        .collect(() -> new StringBuilder(),     // StringBuilder::new
                                (sb, str) -> sb.append(str),    // StringBuilder::append
                                (sb1, sb2) -> sb1.append(sb2)); // StringBuilder::append
        System.out.println(word); // adjudicate


    }

    public static void exampleCollectorsWithJoiningAndAverage(){
        System.out.println("Example Using Joining");
        String s = Stream.of("cake","biscuits","apple tart")
                .collect(Collectors.joining(", "));
        System.out.println(s); //cake, biscuits, apple tart

        System.out.println("Example Using Averaging");
        Double avg = Stream.of("cake","biscuits","apple tart")
                .collect(Collectors.averagingInt(st -> st.length()));
        System.out.println(avg); //7.333333333333333
    }

    private static void exampleCollectionIntoMaps() {
        //Collection into Maps: Two functions required: the first function tells the
        // collector how to create the key; the second function tells the collector
        // how to create the value

        // We want a map: dessert name -> number of characters in dessert name
        // Output:
        //    {biscuits=8, cake=4, apple tart= 10}
        System.out.println("Example Using Collection Into Maps");
        Map<String, Integer> map =
            Stream.of("cake","biscuits","apple tart")
                    .collect(
                        Collectors.toMap(s -> s,           // Function for the key
                                         s -> s.length())  // Function for the value
                    );
        System.out.println(map);
    }

    public static void example2CollectionIntoMaps(){
        /** We want a map: number of characters in dessert name -> dessert name
         *  However, 2 of the desserts have tha same length (cake and tart) and as
         *  length is our key and we can't have duplicate keys, this leads to an
         *  exception as Java does not know what to do ...
         *
         *  IllegalStateException: Duplicate key 4 (attempted merging values cake and tart)
         *  To get around this, we can supply a merge function, whereby we append the
         *  colliding keys values together.
         */
        System.out.println("Example Using Collection Into Maps 2");
        Map<Integer,String> map =
                Stream.of("cake","biscuits","tart")
                        .collect(
                               Collectors.toMap(s -> s.length(),   //key is the length
                                       s -> s,                     //value is the length
                                       (s1,s2) -> s1+","+s2)       //Merge function - what to
                                                                   // do if we have duplicate keys
                                                                   //  - append the values
                        );
            System.out.println(map); //{4=cake,tart, 8=biscuits}
    }

    public static void example3CollectionIntoMapsUsingHashMap(){
        /**
         * The maps returned are HashMaps but this is not guaranteed. What if we wanted
         *  a TreeMap implementation so our keys would be sorted. The last argument
         *  caters for this.
         */
        System.out.println("Example Using Collection Into TreeMap Implementation");
        TreeMap<String, Integer> map = Stream.of("cake","biscuits","apple tart","cake")
                .collect(
                        Collectors.toMap(s -> s,                     // Key is the String
                                         s -> s.length(),            // Value is the Length of the String
                                         (len1,len2) -> len1+len2,   // what to do if we have duplicates keys - add the *values*
                                () -> new TreeMap<>()));             // TreeMap::new works
        System.out.println(map); //{apple tart=10, biscuits=8, cake=8}  Note: cake maps to 8, cake exists 2 times in the stream
        System.out.println(map.getClass()); //class java.util.TreeMap



    }






}
