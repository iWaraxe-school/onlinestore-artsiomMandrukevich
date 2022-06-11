package by.issoft.store.Helper;

import by.issoft.domain.Category;
import by.issoft.domain.CategoryFactory;
import by.issoft.domain.CategoryType;
import by.issoft.store.Database.SQLStatements;
import by.issoft.store.Store;

import java.util.*;

import static by.issoft.store.Database.SQLInstructions.INSERT_INTO_PRODUCT;
import static by.issoft.store.Database.SQLInstructions.SELECT_FROM_CATEGORY;

public class StoreHelper {

    Store store;

    RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

    SQLHelper sqlHelper = new SQLHelper();
    SQLStatements sqlStatements = new SQLStatements();

    public StoreHelper(Store store) {
        this.store = store;
    }

    private Map<Category, Integer> createMapOfCategoryFromDataBase(){

        // Connect to DB, insert rows into Category table from CategoryType enum
        sqlHelper.startWorkWithDatabase();
        sqlHelper.insertCategoryFromEnumCategoryTypeIntoCategoryTable();

        // Select list of category from DataBase
        List<String> categoryListDB = sqlHelper.getListOfCategoryFromCategoryTable(sqlStatements.executeStatementQuery(SELECT_FROM_CATEGORY));

        // Create map of category from DataBase
        Map<Category, Integer> mapOfCategoryByDataBase = new HashMap<>();
        CategoryFactory categoryFactory = new CategoryFactory();
        for(String categoryTypeFromDB : categoryListDB){
            mapOfCategoryByDataBase.put(categoryFactory.getCategory(CategoryType.valueOf(categoryTypeFromDB.toUpperCase())), randomStorePopulator.setRandomInt());
        }
        return mapOfCategoryByDataBase;
    }

    public void fillOutProductList() {
        Map<Category, Integer> categoryProductList = createMapOfCategoryFromDataBase();

        for(Map.Entry<Category, Integer> fillEntry : categoryProductList.entrySet()) {
            for (int i = 0; i< fillEntry.getValue(); i++){
//                // I commented this part specially, because I want to store products only into DB
//                Product product = new Product(
//                        randomStorePopulator.setName(fillEntry.getKey().getName()),
//                        randomStorePopulator.setRate(),
//                        randomStorePopulator.setPrice()
//                );
//                // set Category and Product to the STORE
//                 fillEntry.getKey().setProductList(product);

                // insert  Category and Product to the STORE
                sqlStatements.executeInsertIntoProductOrPurchaseTable(INSERT_INTO_PRODUCT,
                        randomStorePopulator.setName(fillEntry.getKey().getName()),
                        randomStorePopulator.setRate(),
                        randomStorePopulator.setPrice(),
                        fillEntry.getKey().getName());
            }
//            // I commented this part specially, because I want to store products only into DB
//            this.store.setProductCategoryList(fillEntry.getKey());
        }
    }
}
