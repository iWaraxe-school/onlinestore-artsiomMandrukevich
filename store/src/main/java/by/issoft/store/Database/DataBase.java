package by.issoft.store.Database;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {

    public Connection connection;

    @SneakyThrows
    private DataBase(){
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }


    private static class DataBaseSingletoneHelper{
        private static final DataBase dataBaseInstance = new DataBase();
    }


    public static DataBase getInstance(){
        return DataBase.DataBaseSingletoneHelper.dataBaseInstance;
    }

}
