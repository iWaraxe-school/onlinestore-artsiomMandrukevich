package by.issoft.store.Database;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLStatements {

    DataBase dataBase = DataBase.getInstance();

    @SneakyThrows
    public void executeStatement(String statementInstruction){
        try (Statement stmt = dataBase.connection.createStatement()) {
            stmt.execute(statementInstruction);
        }
    }

    @SneakyThrows
    public ResultSet executeStatementQuery(String statementInstruction){
        Statement stmt = dataBase.connection.createStatement();
        return stmt.executeQuery(statementInstruction);
    }

    @SneakyThrows
    public void executeInsertIntoCategory(String insertInstruction, String categoryName){
        try(PreparedStatement preparedStatement = dataBase.connection.prepareStatement(insertInstruction)){
            preparedStatement.setString(1, categoryName);
            boolean row = preparedStatement.execute();
        }
    }

    @SneakyThrows
    public void executeInsertIntoProductOrPurchaseTable(String insertInstruction, String nameProduct, int rateProduct, int priceProduct, String categoryProduct){
        try(PreparedStatement preparedStatement = dataBase.connection.prepareStatement(insertInstruction)){
            preparedStatement.setString(1, nameProduct);
            preparedStatement.setInt(2, rateProduct);
            preparedStatement.setInt(3, priceProduct);
            preparedStatement.setString(4, categoryProduct);
            boolean row = preparedStatement.execute();
        }
    }


}
