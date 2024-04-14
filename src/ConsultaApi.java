import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {
    public Tasa buscaTasaEnAPi(String tasaInicio ,String tasaCambio ) {

        String direction = "https://v6.exchangerate-api.com/v6/3631d2f9123e0375f1705ad9/pair/" + tasaInicio + "/" + tasaCambio;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direction))
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Tasa.class);
        } catch (Exception e) {
            throw new RuntimeException("No encontre esa moneda.");
        }
    }
}
