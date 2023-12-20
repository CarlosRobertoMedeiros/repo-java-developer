package br.com.roberto.estudos.microservicealunos.core.entities;

import java.util.Arrays;
import java.util.List;

public abstract class UsersRegistred {
    public final static List<User> users = Arrays.asList(new User(1L, "Antonio", "123456"),
            new User(2L, "Nunes", "123456"),
            new User(3L, "paulotrc", "123456")
    );


    public static void authenticate(User filteredUser) {
        //continuar daqui
    }
}
