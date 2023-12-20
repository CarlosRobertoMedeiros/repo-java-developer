package br.com.roberto.estudos.microservicealunos.core;

import br.com.roberto.estudos.microservicealunos.core.entities.Product;
import br.com.roberto.estudos.microservicealunos.core.entities.User;
import br.com.roberto.estudos.microservicealunos.core.entities.UsersRegistred;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShoppingCartTest {

    @Test
    @DisplayName("should Add Products To Cart")
    void shouldAdd2ProductToCart(){
        ShoppingCart shoppingCart = new ShoppingCart();

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Motorola G84 - Paulo's Product");
        product.setValue(1599.99);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductName("Motorola G84 - Alane's Product");
        product2.setValue(1599.99);

        shoppingCart.addProduct(product);
        shoppingCart.addProduct(product2);
        Assertions.assertEquals(2,shoppingCart.getProducts().size());
    }

    @Test
    @DisplayName("Should Authenticate User")
    void shouldAuthenticateUser(){
        User user = new User(3L,"paulotrc","12345");
        Optional<User> filteredUser =  UsersRegistred.users.stream()
                .filter(userToFind -> userToFind.getId()==3L)
                        .findFirst();

        if (filteredUser.isPresent()) {
            UsersRegistred.authenticate(filteredUser.get());
        }






    }

}
