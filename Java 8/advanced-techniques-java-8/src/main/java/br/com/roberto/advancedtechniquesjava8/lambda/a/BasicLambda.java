package br.com.roberto.advancedtechniquesjava8.lambda.a;

interface I {
    void m(); //a functional interface has only one method abstract
}
public class BasicLambda {
    public static void main(String[] args) {
        //Pre-Java 8
        I i = new I() {
            @Override
            public void m() {
                System.out.println("I::m()");
            }
        };
        i.m();

        //Java 8 Lambda Expressions
        I lambda1 = () -> {
            System.out.println("Lambda Version");
        };

        I lambda2 = () -> System.out.println("Lambda Version 2");

        lambda1.m();
        lambda2.m();
    }
}
