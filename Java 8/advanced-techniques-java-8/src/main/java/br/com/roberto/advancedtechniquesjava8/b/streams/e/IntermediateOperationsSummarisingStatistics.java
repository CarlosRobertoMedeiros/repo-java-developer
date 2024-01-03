package br.com.roberto.advancedtechniquesjava8.b.streams.e;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class IntermediateOperationsSummarisingStatistics {

    public static void main(String[] args) {
        stats(IntStream.of(5,10,15,20));
        stats(IntStream.empty());
    }

    public static void stats(IntStream numbers){
        IntSummaryStatistics intStats = numbers.summaryStatistics(); //Terminal Op.
        int min = intStats.getMin(); //5 (2147483647 if nothing in stream)
        System.out.println(min);

        int max = intStats.getMax(); //20 (-2147483648 if nothing in stream)
        System.out.println(max);

        double average = intStats.getAverage(); //12.5 (0.0 if nothing in stream)
        System.out.println(average);

        long count = intStats.getCount();//4 (0 if nothing in stream)
        System.out.println(count);

        long sum = intStats.getSum();
        System.out.println(sum);//50 (0 if nothing in stream)
    }

}
