package api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexAPI {
    private static final String BASE_URL = "https://gutendex.com/books";

    public String fetchBookByTitle(String title) throws IOException, InterruptedException {
        String url = BASE_URL + "?search=" + title;
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Respuesta de la API: " + response.body()); // Imprimir la respuesta de la API

        if (response.statusCode() != 200) {
            throw new IOException("Error al conectar con la API. Código de estado: " + response.statusCode());
        }

        return response.body(); // Retorna el cuerpo de la respuesta
    }

}
