package utils.gson;

import com.google.gson.GsonBuilder;

/**
 *  Provides more structured output to the console.
 *  This class uses private constructor to prevent initialization.
 */

public class GsonFormatter {

    private GsonFormatter() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }
    public static void printObjectToJson(Object object) {
        com.google.gson.Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        System.out.println(json);
    }
}
