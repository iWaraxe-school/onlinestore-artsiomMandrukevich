package by.issoft.store.Helper;
import by.issoft.domain.CategoryType;
import by.issoft.store.Database.DataBase;
import by.issoft.store.Database.SQLStatements;
import by.issoft.tools.sort.SortValues;
import by.issoft.tools.sort.XmlParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.issoft.store.Database.SQLInstructions.*;
import static by.issoft.store.Database.SQLInstructions.CREATE_PRODUCT_TABLE;
import static by.issoft.tools.sort.StoreComparator.PATH_TO_XML_SORT_CONFIG;

@Slf4j
public class SQLHelper {

    DataBase dataBase = DataBase.getInstance();
    SQLStatements sqlStatements = new SQLStatements();

    public void startWorkWithDatabase(){
        sqlStatements.executeStatement(DROP_CATEGORY_TABLE);
        sqlStatements.executeStatement(DROP_PRODUCT_TABLE);
        sqlStatements.executeStatement(DROP_PURCHASE_TABLE);
        sqlStatements.executeStatement(CREATE_CATEGORY_TABLE);
        sqlStatements.executeStatement(CREATE_PRODUCT_TABLE);
        sqlStatements.executeStatement(CREATE_PURCHASE_TABLE);
    }

    public void insertCategoryFromEnumCategoryTypeIntoCategoryTable(){
        for(CategoryType categoryType : CategoryType.values()){
            sqlStatements.executeInsertIntoCategory(INSERT_INTO_CATEGORY, categoryType.name());
        }
    }

    @SneakyThrows
    public List<String> getListOfCategoryFromCategoryTable(ResultSet rs){
        List<String> categoryListDB = new ArrayList<>();
        while (rs.next())
        {
            categoryListDB.add(rs.getString(1));
        }
        return categoryListDB;
    }

    @SneakyThrows
    public void selectFromProductTable(ResultSet rsProduct){
        while (rsProduct.next())
        {
            String productName = rsProduct.getString(1);
            int productRate = rsProduct.getInt(2);
            int productPrice = rsProduct.getInt(3);

            System.out.println(productName + " " + productRate + " " + productPrice);
        }
    }

    @SneakyThrows
    public void selectSortFromProductTable(){
        XmlParser xmlParser = new XmlParser();
        xmlParser.xmlReadAndParse(PATH_TO_XML_SORT_CONFIG);
        Map<String, SortValues> sortMap = xmlParser.getSortMap();

        String SELECT_FROM_PRODUCT_SORT = "SELECT NAME, RATE, PRICE FROM PRODUCT ORDER BY " +
                "NAME " + sortMap.get("name") +
                ", RATE " + sortMap.get("rate") +
                ", PRICE " + sortMap.get("price") + ";";

        System.out.println("RUN " + SELECT_FROM_PRODUCT_SORT);
        selectFromProductTable(sqlStatements.executeStatementQuery(SELECT_FROM_PRODUCT_SORT)
        );
    }

    @SneakyThrows
    public void selectTop5FromProductTable(){
        System.out.println("SELECT TOP 5 PRODUCT FROM DATABASE");
        selectFromProductTable(sqlStatements.executeStatementQuery(SELECT_TOP_5_PRODUCT_FROM_PRODUCT)
        );
    }

    @SneakyThrows
    public void insertRandomProductIntoPurchaseTable(){
        ResultSet rsRandomProduct = sqlStatements.executeStatementQuery(SELECT_RANDOM_PRODUCT_FROM_PRODUCT);

        while (rsRandomProduct.next())
        {
            sqlStatements.executeInsertIntoProductOrPurchaseTable(INSERT_INTO_PURCHASE,
                    rsRandomProduct.getString(1),
                    rsRandomProduct.getInt(2),
                    rsRandomProduct.getInt(3),
                    rsRandomProduct.getString(4));
        }
    }

    @SneakyThrows
    public void selectFromPuchaseTable(){
        System.out.println("SELECT PURCHASES FROM DB");
        selectFromProductTable(sqlStatements.executeStatementQuery(SELECT_FROM_PURCHASE));
    }

    public void deleteFromPurchaseTable(){
        System.out.println("DELETE FROM PURCHASE TABLE");
        sqlStatements.executeStatement(DELETE_FROM_PURCHASE);
    }

    @SneakyThrows
    public void closeSQLConnection(){
        sqlStatements.executeStatement(DROP_CATEGORY_TABLE);
        sqlStatements.executeStatement(DROP_PRODUCT_TABLE);
        sqlStatements.executeStatement(DROP_PURCHASE_TABLE);
        dataBase.connection.close();
    }

}
