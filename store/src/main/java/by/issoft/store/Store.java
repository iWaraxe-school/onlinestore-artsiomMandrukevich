package by.issoft.store;

import by.issoft.domain.Category;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<Category> productCategoryList;

    public Store(){
        this.productCategoryList = new ArrayList<>();
    }

    public void setProductCategoryList(Category category) {
       this.productCategoryList.add(category);
    }

    public void printProductFromCategory(){
        for(Category outputList : this.productCategoryList) {
            System.out.println("Category = " + outputList.getName());
            outputList.printAllProducts();
        }
    }
}
