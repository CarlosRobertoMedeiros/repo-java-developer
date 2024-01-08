package br.com.roberto.advancedtechniquesjava8.c.collectionsandgenerics.b;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class CollectionSet {
    /*
    *  Hashing is about using memory more efficiently e.g. making searching faster.
    * There are two steps:
    *    1. Find the bucket using hashCode()
    *    2. Find the object using equals
    *
    * Thus, hashCode() and equals() are linked when using hash based collections.
    * Therefore, if you are using a collection with "hash" in its name and you override
    * hashCode(), you must override equals() also (vice and versa)
    * */

    public static void main(String[] args) {
        usingSetFactoryMethods();
        usingTreeSet();
        usingHashSet();
        linkedHashSetExample();
    }

    public static void usingSetFactoryMethods(){
        Set<String> of = Set.of("a","b","c");
        Set<String> copy = Set.copyOf(of);

        of.add("d");         //UnsupportedOperationException
        copy.add("d");       //UnsupportedOperationException

        of.remove("a");
    }

    public static void usingTreeSet(){
        //SUO - Sets are unique and Ordered
        Set<String> names = new TreeSet<>();
        names.add("John");
        names.add("John");
        names.add("Helen");
        names.add("Anne");
        //No duplicates, elements are sorted numerically
        System.out.println(names); //[Anne, Helen, John]

        Set<Integer> numbers = new TreeSet<>();
        numbers.add(23);
        numbers.add(Integer.valueOf("21"));
        numbers.add(Integer.valueOf("11"));
        numbers.add(99);
        //No duplicates, elements are sorted numerically
        System.out.println(numbers); //[11, 21, 23, 99]
    }

    public static void usingHashSet(){
        //HashSet - The order not matter
        Set<Contact> contactsHS = new HashSet<>();
        contactsHS.add(new Contact(45,"zoe"));
        contactsHS.add(new Contact(45,"zoe"));
        contactsHS.add(new Contact(34,"alice"));
        contactsHS.add(new Contact(35,"andrew"));
        contactsHS.add(new Contact(36,"brian"));
        contactsHS.add(new Contact(37,"carol"));
        /*
        * Output:
        *   brian, 36
        *   andrew, 35
        *   carol, 37
        *   alice, 34
        *   zoe, 45
        * */
        for(Contact contact:contactsHS){
            System.out.println(contact);
        }
        System.out.println();
    }

    public static void linkedHashSetExample(){
        /* LinkedHashSet
        *  API: this implementation differs from HashSet in that it maintains
        *  a double-linked list(lista duplamente encadeada) running through all fo its entries.
        * This linked list defines the interation ordering, which is the order in which elements
        * where inserted into the set (insertion-order)
        *
        * This implementation spares its clients from the unspecified, generally chaotic ordering
        * provided by HashSet, without incurring the increased cost
        * associated with TreeSet
        **/
        Set<Contact> contactsHS = new LinkedHashSet<>();
        contactsHS.add(new Contact(45,"zoe"));
        contactsHS.add(new Contact(45,"zoe")); //"zoe" only added once (Set)
        contactsHS.add(new Contact(34,"alice"));
        contactsHS.add(new Contact(35,"andrew"));
        contactsHS.add(new Contact(36,"brian"));
        contactsHS.add(new Contact(37,"carol"));
         /*
         * Output:
         *   zoe, 45
         *   alice, 34
         *   andrew, 35
         *   brian, 36
         *   carol, 37
         *
         * */
        for (Contact contact : contactsHS){
            System.out.println(contact);
        }



    }
}
