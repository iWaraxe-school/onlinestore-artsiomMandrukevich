package by.issoft.store.Database;

public class SQLInstructions {

    public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS CATEGORY(NAME VARCHAR(255)); COMMIT;";
    public static final String DROP_CATEGORY_TABLE = "DROP TABLE IF EXISTS CATEGORY; COMMIT;";
    public static final String INSERT_INTO_CATEGORY = "INSERT INTO CATEGORY VALUES(?);COMMIT;";
    public static final String SELECT_FROM_CATEGORY = "SELECT NAME FROM CATEGORY;";

    public static final String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS PRODUCT(NAME VARCHAR(255), RATE INT, PRICE INT, CATEGORY_NAME VARCHAR(255)); COMMIT;";
    public static final String INSERT_INTO_PRODUCT = "INSERT INTO PRODUCT VALUES(?, ?, ?, ?); COMMIT;";
    public static final String SELECT_FROM_PRODUCT = "SELECT NAME, RATE, PRICE, CATEGORY_NAME FROM PRODUCT;";
    public static final String SELECT_TOP_5_PRODUCT_FROM_PRODUCT = "SELECT NAME, RATE, PRICE FROM PRODUCT ORDER BY PRICE DESC LIMIT 5;";
    public static final String SELECT_RANDOM_PRODUCT_FROM_PRODUCT = "SELECT NAME, RATE, PRICE, CATEGORY_NAME FROM PRODUCT ORDER BY RAND() LIMIT 1;";
    public static final String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS PRODUCT; COMMIT;";

    public static final String CREATE_PURCHASE_TABLE = "CREATE TABLE IF NOT EXISTS PURCHASE(NAME VARCHAR(255), RATE INT, PRICE INT, CATEGORY_NAME VARCHAR(255)); COMMIT;";
    public static final String INSERT_INTO_PURCHASE = "INSERT INTO PURCHASE VALUES(?, ?, ?, ?); COMMIT;";
    public static final String SELECT_FROM_PURCHASE = "SELECT NAME, RATE, PRICE, CATEGORY_NAME FROM PURCHASE;";
    public static final String DELETE_FROM_PURCHASE = "DELETE FROM PURCHASE; COMMIT;";
    public static final String DROP_PURCHASE_TABLE = "DROP TABLE IF EXISTS PURCHASE; COMMIT;";


}
