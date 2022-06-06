package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.CategoryFactory;
import by.issoft.domain.CategoryType;
import by.issoft.domain.Product;
import by.issoft.store.Helper.RandomStorePopulator;
import by.issoft.tools.sort.StoreComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Store {

    private List<Category> productCategoryList;
    private CopyOnWriteArrayList<Category> purchasedCategory;

    StoreComparator storeComparator = new StoreComparator();

    private Store(){
        this.productCategoryList = new ArrayList<>();
        this.purchasedCategory = new CopyOnWriteArrayList<>();
    }


    private static class SingletoneHelper{
        private static final Store storeInstance = new Store();
    }


    public static Store getInstance(){
        return SingletoneHelper.storeInstance;

    }

    // Store's methods

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

    // Purchase's methods

    public Category createRandomPurchaseGood(){
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        Category category = new CategoryFactory().getCategory(CategoryType.randomDirection());
        Product product = new Product(
                randomStorePopulator.setName(category.getName()),
                randomStorePopulator.setRate(),
                randomStorePopulator.setPrice()
        );
        category.setProductList(product);
        return category;
    }

    public void setPurchaseGoods(){
        this.purchasedCategory.add(createRandomPurchaseGood());
    }


    public void printPurchaseCollection(){
        System.out.println("Size of purchases is " + this.purchasedCategory.size());
        for(Category outputList : this.purchasedCategory) {
            System.out.println("Category = " + outputList.getName());
            printProduct(outputList.getProductList());
        }
    }

    public void cleanUpPurchasedCollection(){
        this.purchasedCategory.clear();
    }

}
