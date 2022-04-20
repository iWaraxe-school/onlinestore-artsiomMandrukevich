package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public String name;
    public List<Product> product;

    public Category(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

//    TO DO
//    need understand what to do with prodict list
//    public List<Product> getProduct() {
//        return product;
//    }

}
