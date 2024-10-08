package sn.gerardo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Registros {
    public void guardarRegistro(String origenMoneda, String destinoMoneda, double monto) {
        // Obtener la hora actual
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaActual = ahora.format(formatter);

        // Crear un objeto JSON
        JsonObject registro = new JsonObject();
        registro.addProperty("origenMoneda", origenMoneda);
        registro.addProperty("destinoMoneda", destinoMoneda);
        registro.addProperty("monto", monto);
        registro.addProperty("hora", horaActual);

        // Convertir a JSON y guardar en el archivo
        try (FileWriter file = new FileWriter("registros.txt", true)) {
            Gson gson = new Gson();
            file.write(gson.toJson(registro) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void main (String[] args) {
//    Registros registros = new Registros();
//        String origenMoneda = "ars";
//        String destinoMoneda = "brl";
//        Double monto = 113.33;
//        registros.guardarRegistro(origenMoneda, destinoMoneda, monto);
//    }

}
