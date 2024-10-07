package sn.gerardo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la moneda de origen: ");
        String origenMoneda = scanner.nextLine().toUpperCase();

        System.out.println("Ingrese el monto: ");
        double monto = scanner.nextDouble();

        scanner.nextLine(); // Clear the buffer

        System.out.println("Ingrese la moneda destino: ");
        String destinoMoneda = scanner.nextLine().toUpperCase();

        ConversionRates conversionRates = new ConversionRates();
        double tasaCambio = conversionRates.consultarTasaCambio(origenMoneda, destinoMoneda);

        double montoConvertido = monto * tasaCambio;
        System.out.println("El monto convertido es: " + montoConvertido + " " + destinoMoneda);

    }
}