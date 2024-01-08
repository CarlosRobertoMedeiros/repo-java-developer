package br.com.roberto.advancedtechniquesjava8.c.collectionsandgenerics.b;

import java.util.*;

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
        //factoryMethods();
        //arrayListExample();
        //stackExample();
        linkedListExample();
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

    public static void arrayListExample(){
        //LOD - Lord of the Dance
        //    = Lists maintain Order(index) and allow duplicates
        List<String> list = new ArrayList<>();
        list.add("Alan");
        list.add("Alan");
        list.add(1,"Sean");
        list.add("Mary");
        list.add("Mary");
        System.out.println(list); //[Alan, Sean, Alan, Mary, Mary]
        System.out.println(list.get(1)); //[Sean]
        list.remove(0); //results in: [Sean, Alan, Mary, Mary]
        list.remove("Mary"); //Only first Mary is removed [Sean, Alan, Mary]
        System.out.println(list); //[Sean, Alan, Mary]
        list.set(0,"Jack"); //[Jack, Alan, Mary]
        //replaceAll(UnaryOperator<E> op) - UnaryOperator is a Function<T,T> where the
        //                                  input and output are the same type
        list.replaceAll(name -> name + " Kennedy");
        System.out.println(list); //[Jack Kennedy, Alan Kennedy, Mary Kennedy]
    }

    public static void stackExample(){
        // Stack is a LIFO structure (Last in First Out) - we can manipulate one end only
        // Using the stack type as the reference type so we get access to the push(),pop() and peek() methods.
        Stack<String> stack = new Stack<>();
        stack.push("Andrea");
        stack.push("Barbara");
        stack.push("Caroline");
        System.out.println(stack); //[Andrea, Barbara, Caroline]

        System.out.println("Top of Stack: "+stack.peek()); //Caroline
        System.out.println("Popped: "+stack.pop()); //Caroline - [Andrea, Barbara]
        stack.push("Helen");
        System.out.println(stack); //[Andrea, Barbara, Helen]
    }

    public static void linkedListExample(){
        //A double-linked (Lista buplamente encadeada) list. We can manipulate both ends.
        LinkedList<String> names = new LinkedList<>();
        names.add("Colin"); //[Colin]
        names.add("David"); //[Colin, David]
        names.addFirst("Brian"); //[Brian, Colin, David]
        names.addLast("Edward"); // [Brian, Colin, David, Edward]
        System.out.println(names);  // [Brian, Colin, David, Edward]

        names.remove("David");   // [Brian, Colin, Edward]
        names.removeFirst();        //[Colin, Edward]
        names.removeLast();         //[Colin]
        System.out.println(names);  //[Colin]
    }




}
