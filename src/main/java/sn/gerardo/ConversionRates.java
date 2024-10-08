package sn.gerardo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversionRates {
    private final Dotenv dotenv = Dotenv.configure().load();
    private final String API_KEY = dotenv.get("API_KEY");
    private  final String API_URL = "https://v6.exchangerate-api.com/v6/"+API_KEY+"/latest/";

    public double consultarTasaCambio(String origenMoneda, String destinoMoneda) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + origenMoneda))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(response.body(), JsonObject.class);
            return json.getAsJsonObject("conversion_rates").get(destinoMoneda).getAsDouble();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
