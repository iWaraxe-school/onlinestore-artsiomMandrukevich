package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.List;

public class Bike extends Category {

    public Bike(String name, List<Product> product){
        super(name, product);
    }

}
