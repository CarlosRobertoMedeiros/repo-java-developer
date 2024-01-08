package br.com.roberto.advancedtechniquesjava8.c.collectionsandgenerics.a;

public class CommonCollectionMethodSet {
    /*
    * Set - Collections with no duplicates elements
    * HashSet
    *   - Unsorted, unordered Set; uses the hascode of the
    * object being inserted; the more efficient your hashCode() implementation,
    * the better access performance you will get.
    *
    *   - Use this class when you want a collection with no duplicates and you don't care
    * about order when you throw it.
    *
    * LinkedHashSet
    *   - An ordered version of HashSet(insertion order)
    *   - Elements are double-linked (Lista buplamente encadeada) to each other
    *   - Use this class instead of HashSet when you care about
    * the interactor order
    *
    * TreeSet
    *   - a sorted collection ("Tree")
    *   - elements can be sorted according to their "natural order" - for String's,
    * the natural order is alphabetic; for Integer's, the natural order is numeric.
    *   - elements can also be sorted according to a custom order by providing
    * a comparator at creation time
    *
    *  Popular Set Methods
    *
    *  Set<E>      Set.of(E... elements)                           returns an immutable set containing
    *                                                              elements specified.
    *  Set<E>      Set.copyOf(Collection<? extends E> coll)        returns an immutable set containing
    *                                                              elements of the given collection
    * */


}
