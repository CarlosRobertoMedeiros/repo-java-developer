package br.com.roberto.advancedtechniquesjava8.c.collectionsandgenerics.a;

public class CommonCollectionQueue {
    /**
     *  Collection - Queue
     *
     *  1- Queue - a collection that specifies the order in which
     *  elements are to be processed
     *      - Typically the order is FIFO (First In First Out)
     *
     *      - Exceptions are priority queues(order is natural ordering or according to a supplied comparator)
     *  a LIFO (Last in First Out) queues (Stacks)
     *
     *      - LinkedList implements Queue; basic queue can be handled with a LinkedList
     *
     *  2- PriorityQueue orders the elements relative to each other such that "priority-in,priority-out"
     *  (as opossed to a FIFO or LIFO)
     *      The elements are either ordered by natural order or by a custom order via a comparator
     *      Elements that are sorted first will be accessed first.
     *
     *  3- Deque
     *      deque("double ended queue") and is pronounced "deck".
     *      access from both ends permitted
     *      can be used as both FIFO(queue)) and LIFO(stack)
     *      ArrayDeque
     *        expandale-array implmentation of the Deque interface (no capacity restrictions)
     *        API: "likely to be faster than Stack when used as a stack, and faster than
     *        LinkedList when used as a queue"
     *
     * Popular Queue Methods
     *
     *             Throws Exception    Return special value
     *   Examine   element()           peek()
     *   Insert    add(e)              offer(e)
     *   Remove    remove()            poll()
     *
     * Obs: The most common method are peek(), offer() and poll() as they do not throw exceptions.
     * POP is useful for remembering them.
     *
     *
     * Popular Deque Methods Head(First Element)
     *                   Throws Exception    Return special value
     *         Examine   getFirst()          peekFirst()
     *         Insert    addFirst(e)         offerFirst(e)
     *         Remove    removeFirst()       pollFirst()
     *
     * Popular Deque Methods Tail(Last Element)
     *                   Throws Exception    Return special value
     *         Examine   getLast()           peekLast()
     *         Insert    addLast(e)          offerLast(e)
     *         Remove    removeLast()        pollLast()
     *
     *
     *  Using Deque as a queue
     *
     *                        Throws Exception    Return special value
     *         Examine        element()           getFirst()
     *                        peek()              peekFirst()
     *
     *         Insert(end)    add(e)              addLast(e)
     *                        offer(e)            offerLast(e)
     *
     *         Remove(front)  remove()            removeFirst()
     *                        poll()              poolFirst()
     *
     *
     *
     *  Using Deque as a stack  //Beginning of deque is the "top" of the stack
     *         stack method    Equivalent Deque method
     *         push(e)         addFirst()
     *         pop()           removeFirst()
     *         peek()          getFirst()
     * */
}
