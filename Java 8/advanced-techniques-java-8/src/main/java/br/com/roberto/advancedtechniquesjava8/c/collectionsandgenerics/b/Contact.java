package br.com.roberto.advancedtechniquesjava8.c.collectionsandgenerics.b;

public class Contact {
    /*
     * Hashing is about using memory more efficiently e.g. making searching faster.
     * There are two steps:
     *    1. Find the bucket using hashCode()
     *    2. Find the object using equals
     *
     * Thus, hashCode() and equals() are linked when using hash based collections.
     * Therefore, if you are using a collection with "hash" in its name and you override
     * hashCode(), you must override equals() also (vice and versa)
     *
     *  * */
    private int age;
    private String name;

    public Contact(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int hashCode() {     //both instance variable 'age' and 'name' are used
        int hash = 7;
        hash = 89 * hash + this.age;
        hash = 89 * hash + this.name.length(); //a week algorithm - demo purposes
        return hash;
    }

    @Override
    public boolean equals(Object obj) { //same instance variables used as in equals()
        if (obj instanceof Contact) {
            Contact otherContact = (Contact) obj;
            return this.name.equals(otherContact.name)
                    && this.age == otherContact.age;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
