package br.com.roberto.advancedtechniquesjava8.b.streams.a;

import java.util.Arrays;
import java.util.List;

public class InitialExample {
    public static void main(String[] args) {
        List<Double> temperature = Arrays.asList(20.2,23.5,31.5,33.4);
        System.out.println("Number of temperature > 30 is: "+
                temperature.stream() //create the stream
                .peek(System.out::println) //show the value
                .filter(temp -> temp > 30.0) //filter it
                .peek(System.out::println)  //show the value
                .count()); //2


    }

}
