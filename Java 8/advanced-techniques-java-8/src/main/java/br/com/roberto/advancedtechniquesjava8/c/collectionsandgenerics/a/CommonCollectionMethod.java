package br.com.roberto.advancedtechniquesjava8.c.collectionsandgenerics.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CommonCollectionMethod {
    /*
    * Collections
    *
    * Differentiate the following:
    *      collection - lowercase 'c'; represent any of the data structures in which objects
    *   are stored and iterated over.
    *
    *      Collection - capital 'C'; this is the java.util.Collection interface that the Set,
    *   List and Queue interfaces extend. "In the image directory exists the 02-java.util.Collection
    *   It is a example of OO hierarchy"
    *
    *       Collections - capital 'C' and ends with 's'; this is the java.util.Collections utility
    *   class which contains a lots of static methods for use with collections
    * */
    public static void main(String[] args) {
        commonCollectionMethod();
    }

    public static void commonCollectionMethod(){
        //Lists allow duplicates
        //Collection<String> col1 = new ArrayList<>(); // AO DESCOMENTAR SIGNIFICA COLEÇÃO MUTÁVEL

        // asList() returns an unmodifiable collection
        // => add() throws an UnsupportedOperationException
        Collection<String> col1 = Arrays.asList("Lucy","April","Lucy"); //AO UTILIZAR SIGNIFICA COLEÇÃO IMMUTABLE

        col1.add("Lucy");
        col1.add("April");
        col1.add("Lucy");
        System.out.println(col1); //[Lucy, April, Lucy]
        System.out.println(col1.remove("Lucy")); //true
        System.out.println(col1); //[April, Lucy]
        System.out.println(col1.isEmpty()); //false
        System.out.println(col1.size()); //2
        System.out.println(col1.contains("John")); //false
        System.out.println(col1.contains("Lucy")); //true
        // removeIf(Predicate) and Predicate == boolean test(T t)
        System.out.println(col1.removeIf(s -> s.startsWith("A")));//true
        col1.forEach(name -> System.out.println(name)); //[Lucy]
        col1.clear();
        col1.forEach(System.out::println); //nothing output
    }

}
