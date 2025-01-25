package utils.gson;

import com.google.gson.GsonBuilder;

public class GsonFormatter {
    public static void printObjectToJson(Object object) {
        com.google.gson.Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        System.out.println(json);
    }
}
