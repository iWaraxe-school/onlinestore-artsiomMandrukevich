package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public String name;
    public List<Product> productList;

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
        for(Integer i = 0; i < productList.size(); i++){
            System.out.println("Product's name " + productList.get(i).getName()
                    + " rate " + productList.get(i).getRate()
                    + " price " + productList.get(i).getPrice()
            );
        }
    }
}
