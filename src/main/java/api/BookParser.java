package api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.Book;

public class BookParser {
    public Book parseBook(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        if (jsonObject == null || !jsonObject.has("results")) {
            System.err.println("El JSON no contiene el campo 'results': " + jsonResponse);
            return null;
        }

        JsonArray results = jsonObject.getAsJsonArray("results");
        if (results.size() == 0) {
            System.err.println("No se encontraron resultados: " + jsonResponse);
            return null;
        }

        JsonObject firstResult = results.get(0).getAsJsonObject();
        String title = firstResult.get("title").getAsString();
        String author = firstResult.getAsJsonArray("authors").get(0)
                .getAsJsonObject().get("name").getAsString();
        String language = firstResult.getAsJsonArray("languages").get(0).getAsString();
        int downloadCount = firstResult.get("download_count").getAsInt();

        return new Book(0, title, author, language, downloadCount);
    }

}
