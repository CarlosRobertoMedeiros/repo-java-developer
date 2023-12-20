package br.com.roberto.estudos.microservicealunos.core.entities;

import org.springframework.boot.autoconfigure.batch.BatchDataSourceScriptDatabaseInitializer;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private List<Product> products = new ArrayList<>();
    private long id;
    private String productName;
    private double value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean addProduct(Product product){
        return products.add(product);
    }

}
