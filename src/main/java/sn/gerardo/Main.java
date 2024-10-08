package sn.gerardo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean contStatus = true;
        System.out.println("Conversor de monedas.");
        System.out.println("GerardoSN - Alura Latam");

        while (contStatus) {
            System.out.println("\nNueva conversion.");

            System.out.println("Ingrese la moneda de origen (ARS - USD - BRL - CLP): ");
            String origenMoneda = scanner.nextLine().toUpperCase();

            System.out.println("Ingrese el monto: ");
            double monto = scanner.nextDouble();
            scanner.nextLine(); //Consumir lo que queda

            System.out.println("Ingrese la moneda destino (ARS - USD - BRL - CLP): ");
            String destinoMoneda = scanner.nextLine().toUpperCase();

            ConversionRates conversionRates = new ConversionRates();
            double tasaCambio = conversionRates.consultarTasaCambio(origenMoneda, destinoMoneda);

            double montoConvertido = monto * tasaCambio;
            BigDecimal montoConvertidoCon4Decimales = new BigDecimal(montoConvertido);
            montoConvertidoCon4Decimales = montoConvertidoCon4Decimales.setScale(4, RoundingMode.HALF_UP);

            System.out.println("El monto convertido es: " + montoConvertidoCon4Decimales + " " + destinoMoneda);

            System.out.println("¿Desea continuar? (SI - NO): ");
            if (scanner.nextLine().toUpperCase().equals("NO")){
                contStatus = false;
                System.out.println("Gracias por utilizar nuestros servicios. \nHasta pronto.");
            }
        }

    }
}