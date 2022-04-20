package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Product> productList;

    public Category(String name) {
        this.name = name;
        this.productList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setProductList(Product product) {
        this.productList.add(product);
    }

    public void printAllProducts(){
        for (Product product : productList) {
            System.out.println("Product's name " + product.getName()
                    + " rate " + product.getRate()
                    + " price " + product.getPrice()
            );
        }
    }
}
