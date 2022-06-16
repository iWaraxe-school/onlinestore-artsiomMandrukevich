package by.issoft.store.Helper;

import com.sun.net.httpserver.HttpExchange;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;

public class JSONHelper {

    @SneakyThrows
    public JSONArray convertResultSetToJSON(ResultSet resultSet){
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int columns = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < columns; i++)
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
            jsonArray.put(obj);
        }
        return jsonArray;
    }

    @SneakyThrows
    public JSONObject convertPostBodyToJSON(HttpExchange exchange){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return new JSONObject(stringBuilder.toString());
    }

    public JSONArray prepareJSONArrayRequestMessage(String messageText){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", messageText);
        jsonArray.put(jsonObject);
        return jsonArray;
    }

}
