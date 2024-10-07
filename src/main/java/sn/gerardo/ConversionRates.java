package sn.gerardo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversionRates {
     private final Dotenv dotenv = Dotenv.configure().load();
     private final String API_KEY = dotenv.get("API_KEY");
    private  final String API_URL = "https://v6.exchangerate-api.com/v6/"+API_KEY+"/latest/";

    public double consultarTasaCambio(String origenMoneda, String destinoMoneda) {
        try {
            String urlString = API_URL + origenMoneda;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(content.toString(), JsonObject.class);
            return json.getAsJsonObject("conversion_rates").get(destinoMoneda).getAsDouble();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
