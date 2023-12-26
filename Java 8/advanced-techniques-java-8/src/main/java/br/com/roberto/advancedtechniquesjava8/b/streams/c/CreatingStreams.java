package br.com.roberto.advancedtechniquesjava8.b.streams.c;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class CreatingStreams {
    public static void main(String[] args) {
        creatingArrayStream();
        creatingAStreamFromACollection();
        creatingAStreamFromACollection2();
        creatingAStreamWithStreamOf();
        creatingAStreamFromAFile();
        //creatingInfinityStreams();
        creatingInfinityStreams2();
    }

    public static void creatingArrayStream(){
        System.out.println("Example 1 - creatingArrayStream");
        Double[] numbers = {1.1,2.2,3.3};
        Stream<Double> stream1 = Arrays.stream(numbers);
        long n = stream1.count();
        System.out.println("Number of elements: "+n);
    }

    public static void creatingAStreamFromACollection() {
        System.out.println("Example 2 - creatingAStreamFromACollection");
        List<String> animals = Arrays.asList("cat","dog","sheep");
        Stream<String> streamAnimals = animals.stream();
        System.out.println("Number of elements: "+streamAnimals.count());
    }

    public static void creatingAStreamFromACollection2() {
        /*
        * stream() is a default method in the Collection interface and therefore
        * is inherited by all classes that implement Collection. Map is not one
        * of those i.e. Map is not a Collection. To bridge between the two, we
        * use the Map method entrySet() to return a Set view of the Map (Set IS-A Collection)
        **/
        System.out.println("Example 3 - creatingAStreamFromACollection2");
        Map<String,Integer> namesToAge = new HashMap<>();
        namesToAge.put("Mike",22);
        namesToAge.put("Mary",24);
        namesToAge.put("Alice",31);
        System.out.println("Number of entries: "+
                namesToAge
                        .entrySet()
                        .stream()
                        .count());
    }

    public static void creatingAStreamWithStreamOf(){
        System.out.println("Example 4 - creatingAStreamWithStreamOf");
        Stream<Integer> streamI = Stream.of(1,2,3);
        System.out.println(streamI.count());

        Stream<String> streamS = Stream.of("a","b","c","d");
        System.out.println(streamS.count());

        Stream<Dog> streamDog = Stream.of(new Dog());
        System.out.println(streamDog.count());

    }
    public static void creatingAStreamFromAFile(){
        System.out.println("Example 5 - creatingAStreamFromAFile");
        List<Cat>cats = loadCats("src/main/resources/Cats.txt");
        cats.forEach(System.out::println);
    }

    public static void creatingInfinityStreams(){
        System.out.println("Example 5 - creatingInfinityStreams");
        Stream<Integer> infStream = Stream.generate(() -> {return (int) (Math.random() * 10);});
        infStream.forEach(System.out::println);

    }

    public static void creatingInfinityStreams2(){
        /**
         * infinite stream of ordered numbers
         *  2,4,5,8,10 ....
         *  iterate(T seed, UnaryOperator<T> fn)
         *    UnaryOperator is a Function<T,T>
         *        T apply(T t)
         * */
        System.out.println("Example 6 - creatingInfinityStreams2");
        Stream<Integer> infStream =
                Stream.iterate(2, n -> n + 2)
                    .limit(10);
        infStream.forEach(System.out::println);
    }

    private static List<Cat> loadCats(String filename) {
        List<Cat> cats = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(filename))){

            stream.forEach(line ->{
                String[] catsArray = line.split("/");
                cats.add(new Cat(catsArray[0], catsArray[1]));
            });
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return cats;
    }



}
class Dog{

}
