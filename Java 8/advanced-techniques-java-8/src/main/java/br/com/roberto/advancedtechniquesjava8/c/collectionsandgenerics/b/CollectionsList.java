package br.com.roberto.advancedtechniquesjava8.c.collectionsandgenerics.b;

import java.util.Arrays;
import java.util.List;

public class CollectionsList {
    /*
    * Collections - List
    *
    * Collections have four basic flavors
    *
    * List: an ordered collection(sequence); provides
    * precise control over access to an element using its integer index;
    * duplicate elements are allowed
    *   ArrayList  - a growable array; fast iteration and fas random access;
    * use when tou are not likely to do much insertion/deletion(shuffling required)
    *
    *   LinkedList - elements are doubly-linked to each other; fast insertion/deletion
    *
    *   Stack - represents a last-in-first-out (LIFO) stack of objects. The Deque interface
    * and its implmentations are more complete and should be used instead.
    *
    *
    *  List<T>   Arrays.asList<T... a>                                      returns a fixed-size list "backed" by the array
    *                                                                       i.e. changes to the list write through to the array
    *                                                                       and vice-versa;
    *                                                                       cannot add/delete elements but can replace elements
    *
    *  List<E>   List.of(E... elements)                                     returns an immutable list containing the elements
    *                                                                       specified
    *
    *  List<E>   List.copyOf(Collection<? Extends E> coll)                  returns an immutable list containing the elements
    *                                                                       of the given collection
    *
    */
    public static void main(String[] args) {
        factoryMethods();
    }

    public static void factoryMethods(){
        String []array = new String[]{"Alpha","Beta","Charlie"};
        List<String> asList = Arrays.asList(array);// 'array' asd 'asList' are now 'backed'
        List<String> of = List.of(array);
        List<String> copy = List.copyOf(asList);

        array[0] = "Delta"; //changes to the array 'write through' to the list
        System.out.println(Arrays.toString(array)); // [Delta, Beta, Charlie]
        System.out.println(asList);                 // [Delta, Beta, Charlie]

        asList.set(1,"Echo"); // changes to the list 'write through' to the array
        System.out.println(Arrays.toString(array));  // [Delta, Echo, Charlie]
        System.out.println(asList);                  // [Delta, Echo, Charlie]

        of.add("FoxTrot");                           // UnsupportedOperationException
        copy.add("FoxTrot");                         // UnsupportedOperationException
        asList.add("Foxtrot");                       // UnsupportedOperationException
    }
}
