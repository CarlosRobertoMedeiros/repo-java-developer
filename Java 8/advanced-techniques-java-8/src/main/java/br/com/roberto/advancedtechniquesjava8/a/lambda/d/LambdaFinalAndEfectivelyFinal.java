package br.com.roberto.advancedtechniquesjava8.a.lambda.d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class LambdaFinalAndEfectivelyFinal {
    String name="";
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>();
        al.add("John");

        int x=12; // final or efectively final
        //boolean test(T t);
        // Lambdas take a snapshot/picture of local variables; these local
        // variables MUST NOT change. Only setting up lambda here.
        Predicate<String> lambda = s -> {
            //x++;
            new LambdaFinalAndEfectivelyFinal().name ="Kennedy";
            System.out.println("x == "+x);
            return s.isEmpty() && x % 2 == 0;
        };
        filterData(al,lambda);
        System.out.println(al);

        new LambdaFinalAndEfectivelyFinal().name="Sean"; // instance/class vars are ok

        // if 'x' was allowed to change, then the method and the lambda would
        // have 2 different views of 'x' !
        //x++
        filterData(al,lambda); // lambda view 'x' as 12
    }

    public static void filterData(List<String> list, Predicate<String> lambda) {
        Iterator<String> i = list.listIterator();
        while (i.hasNext()) {
            if (lambda.test(i.next())){ //executing lambda here
                i.remove();
            }
        }
    }
}
