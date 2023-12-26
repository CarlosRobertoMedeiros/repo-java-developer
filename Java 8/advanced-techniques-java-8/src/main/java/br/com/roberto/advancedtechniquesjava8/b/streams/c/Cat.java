package br.com.roberto.advancedtechniquesjava8.b.streams.c;

public class Cat {
    private String name;
    private String colour;
    public Cat(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
