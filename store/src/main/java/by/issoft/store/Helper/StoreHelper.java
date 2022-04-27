package by.issoft.store.Helper;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StoreHelper {

    Store store;

    public StoreHelper(Store store) {
        this.store = store;
    }

    private Map<Category, Integer> createMapOfCatefory(){
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
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

    public void fillOutProductList() {
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        Map<Category, Integer> categiryProductList = createMapOfCatefory();

        for(Map.Entry<Category, Integer> fillEntry : categiryProductList.entrySet()) {
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
