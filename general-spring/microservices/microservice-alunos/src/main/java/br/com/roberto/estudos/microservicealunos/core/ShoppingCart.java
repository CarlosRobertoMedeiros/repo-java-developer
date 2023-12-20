package br.com.roberto.estudos.microservicealunos.core;

import br.com.roberto.estudos.microservicealunos.core.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> products = new ArrayList<>();

    public boolean addProduct(Product product){
        return products.add(product);
    }

    public List<Product> getProducts(){
        return products;
    }
}
