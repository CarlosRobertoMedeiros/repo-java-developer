package br.com.roberto.advancedtechniquesjava8.b.streams.e;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class OptionalExamples {

    public static void main(String[] args) {
        System.out.println("Example Using calcAverage");
        Optional<Double> optAverage = calcAverage(50,60,70);
        // if you do a get() and the Optional is empty you get:
        //    NoSuchElementException: No value present
        // boolean isPresent() protects us from that.

        if (optAverage.isPresent()) {
            System.out.println(optAverage.get());//60
        }

        // void ifPresent(Customer customer)
        optAverage.ifPresent(System.out::println); //60.0
        // T orElse(T t)
        System.out.println(optAverage.orElse(Double.NaN)); //60.0

        Optional<Double> optAverage2 = calcAverage();//Will return an empty Optional
        System.out.println(optAverage2.orElse(Double.NaN)); //NaN
        // T orElseGet(Supplier<T> supplier)
        System.out.println(optAverage2.orElseGet(() -> Math.random())); //0.03866931957837605

        System.out.println("Example Using doOptionalNull");
        doOptionalNull();

        System.out.println("Example Using doOptionalPrimitiveAverage");
        doOptionalPrimitiveAverage();


    }

    public static Optional<Double> calcAverage(int...scores){
        // a long way to calculate average (Just for showing Optional)
        if (scores.length == 0) return Optional.empty();
        int sum =0;
        for (int score:scores) sum+=score;
        return Optional.of((double)sum / scores.length);
    }

    public static void doOptionalNull(){
        Optional<String> optSk = howToDealWithNull("SK");
        optSk.ifPresent(System.out::println);//SK

        Optional<String> optNull = howToDealWithNull(null);
        System.out.println(optNull.orElseGet(() ->"Empty String")); //Empty Optional



    }
    public static Optional<String> howToDealWithNull(String param){
        //Optional<String> optReturn = param == null ? Optional.empty() : Optional.of(param);
        Optional<String> optReturn =Optional.ofNullable(param); //same as previous line
        return optReturn;
    }

    public static void doOptionalPrimitiveAverage(){
        OptionalDouble optionalAvg = IntStream.rangeClosed(1,10)
                .average();

        // DoubleConsumer - functional interface. functional method is:
        //     void accept(double value)
        optionalAvg.ifPresent((d) -> System.out.println(d));// 5.5
        System.out.println(optionalAvg.getAsDouble()); // 5.5

        // DoubleSupplier - functional interface. functional method is:
        //     double getAsDouble()
        System.out.println(optionalAvg.orElseGet(() -> Double.NaN)); // 5.5
    }

}
