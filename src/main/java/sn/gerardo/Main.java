package sn.gerardo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean contStatus = true;

        mostrarRecuadro("Conversor de monedas.","GerardoSN - Alura Latam.");

        while (contStatus) {
            System.out.println("\nNueva conversion.");

            System.out.println("Ingrese la moneda de origen (ARS - USD - BRL - CLP): ");
            String origenMoneda = scanner.nextLine().toUpperCase();

            System.out.println("Ingrese el monto: ");
            String input = scanner.nextLine(); // Leer la entrada como String
            input = input.replace(",", ".");// Reemplazar la coma por un punto
            double monto = Double.parseDouble(input);// Convertir a double

            System.out.println("Ingrese la moneda destino (ARS - USD - BRL - CLP): ");
            String destinoMoneda = scanner.nextLine().toUpperCase();

            ConversionRates conversionRates = new ConversionRates();
            double tasaCambio = conversionRates.consultarTasaCambio(origenMoneda, destinoMoneda);

            double montoConvertido = monto * tasaCambio;
            BigDecimal montoConvertidoCon4Decimales = new BigDecimal(montoConvertido);
            montoConvertidoCon4Decimales = montoConvertidoCon4Decimales.setScale(4, RoundingMode.HALF_UP);//se redonde el resultado con cuatro ceros

            System.out.println("El monto convertido es: " + montoConvertidoCon4Decimales + " " + destinoMoneda);

            // Guardar registro
            Registros registros = new Registros();
            registros.guardarRegistro(origenMoneda, destinoMoneda, monto);

            System.out.println("¿Desea continuar? (SI - NO): ");
            if (scanner.nextLine().toUpperCase().equals("NO")){
                contStatus = false;
            }
        }

        mostrarRecuadro("Gracias por utilizar nuestros servicios.","Hasta pronto.");

    }

    public static void mostrarRecuadro(String texto1, String texto2) {
        String textoLargo = "Ingrese la moneda destino (ARS - USD - BRL - CLP): ";

        // Calcular el ancho del recuadro
        int ancho = textoLargo.length() + 4; // +4 para los bordes
        String borde = "═".repeat(ancho); // Línea superior del recuadro

        // Imprimir el recuadro
        System.out.println(borde);
        System.out.println("║ " + centrarTexto(texto1, ancho - 2) + " ║");
        System.out.println("║ " + centrarTexto(texto2, ancho - 2) + " ║");
        System.out.println(borde);
    }
    // Metodo para centrar el texto
    public static String centrarTexto(String texto, int ancho) {
        int espacio = ancho - texto.length();
        int izquierda = espacio / 2;
        int derecha = espacio - izquierda;
        return " ".repeat(izquierda) + texto + " ".repeat(derecha);
    }
}