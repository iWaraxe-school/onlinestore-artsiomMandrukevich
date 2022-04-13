package by.issoft.domain;

import java.util.List;

public class Category {
    private String name;
    private List<Product> product;

    public Category(String name, List<Product> product) {
        this.name = name;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProduct() {
        return product;
    }

}
