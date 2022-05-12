package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.tools.sort.StoreComparator;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<Category> productCategoryList;

    public Store(){
        this.productCategoryList = new ArrayList<>();
    }

    StoreComparator storeComparator = new StoreComparator();

    public List<Product> getAllProducts(){
        List<Product> listProduct = new ArrayList<>();
        for(Category outputList : this.productCategoryList) {
            listProduct.addAll(outputList.getProductList());
        }
        return listProduct;
    }

    public void setProductCategoryList(Category category) {
       this.productCategoryList.add(category);
    }

    public void printProduct(List<Product> products){
        for(Product productList : products){
            System.out.println(productList.getName() + " " + productList.getPrice() + " " + productList.getRate());
        }
    }

    public void printProductFromCategory(){
        for(Category outputList : this.productCategoryList) {
            System.out.println("Category = " + outputList.getName());
            printProduct(outputList.getProductList());
        }
    }

    public void printSortProducts(){
        printProduct(storeComparator.sortProduct(getAllProducts()));
    }

    public void printTopProducts(){
        printProduct(storeComparator.top5ProductPrice(getAllProducts()));
    }

}
