package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.List;

public class Phone extends Category {

    public Phone(String name, List<Product> product){
        super(name, product);
    }

}
