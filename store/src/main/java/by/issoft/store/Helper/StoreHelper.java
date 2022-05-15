package by.issoft.store.Helper;

import by.issoft.domain.Category;
import by.issoft.domain.CategoryFactory;
import by.issoft.domain.CategoryType;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StoreHelper {

    Store store;

    RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

    public StoreHelper(Store store) {
        this.store = store;
    }

    private Map<Category, Integer> createMapOfCategoryReflection(){
        Map<Category, Integer> mapOfCategory = new HashMap<>();

        Reflections reflections = new Reflections("by.issoft.domain.categories", new SubTypesScanner());
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for(Class<? extends Category> type : subTypes){
            try{
                mapOfCategory.put(type.getConstructor().newInstance(), randomStorePopulator.setRandomInt());
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e){
                e.printStackTrace();
            }
        }
        return mapOfCategory;
    }

    private Map<Category, Integer> createMapOfCategoryByFactory(){
        Map<Category, Integer> mapOfCategoryByFactory = new HashMap<>();
        CategoryFactory categoryFactory = new CategoryFactory();

        for(CategoryType categoryType : CategoryType.values()){
            mapOfCategoryByFactory.put(categoryFactory.getCategory(categoryType), randomStorePopulator.setRandomInt());
        }
        return mapOfCategoryByFactory;
    }

    public void fillOutProductList() {
        Map<Category, Integer> categoryProductList = createMapOfCategoryByFactory();

        for(Map.Entry<Category, Integer> fillEntry : categoryProductList.entrySet()) {
            for (int i = 0; i< fillEntry.getValue(); i++){
                Product product = new Product(
                        randomStorePopulator.setName(fillEntry.getKey().getName()),
                        randomStorePopulator.setRate(),
                        randomStorePopulator.setPrice()
                );
                fillEntry.getKey().setProductList(product);
            }
            this.store.setProductCategoryList(fillEntry.getKey());
        }
    }
}
