import com.google.gson.JsonArray;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
        JsonArray listaRegistroLocal = new JsonArray();
        ArrayList<String> listaRgistroGlobal = new ArrayList<>();

        EscrituraRelleno relleno = new EscrituraRelleno();
        Scanner lectura = new Scanner(System.in);

        String inicial = "";
        String cambio = "";

        while (true) {

            relleno.escribirMenu();
            System.out.println("Ingresa tu Eleccion: ");
            var eleccion01 = lectura.nextInt();
            lectura.nextLine();

            try {

                if (eleccion01 == 8) {
                    break;
                }

                if (eleccion01 == 7) {
                    relleno.escribirMonedas();
                    System.out.println("Ingrese su moneda 1 o Nativa: ");
                    inicial = lectura.nextLine();
                    System.out.println("ingrese su moneda 2 a Conversion: ");
                    cambio = lectura.nextLine();

                } else {
                    if (eleccion01 == 1) {
                        inicial = "USD";
                        cambio = "ARS";
                    } else if (eleccion01 == 2) {
                        inicial = "ARS";
                        cambio = "USD";
                    } else if (eleccion01 == 3) {
                        inicial = "USD";
                        cambio = "BOB";
                    } else if (eleccion01 == 4) {
                        inicial = "BOB";
                        cambio = "USD";
                    } else if (eleccion01 == 5) {
                        inicial = "USD";
                        cambio = "BRL";
                    } else if (eleccion01 == 6) {
                        inicial = "BRL";
                        cambio = "USD";
                    }
                }


                System.out.println("Ingrese la cantidad a Convertir");
                Double cantidad = lectura.nextDouble();
                lectura.nextLine();
                String moneda1 = inicial.toUpperCase();
                String moneda2 = cambio.toUpperCase();

                // Pedir respuesta de la api y guardar valores en record Tasa
                ConsultaApi consulta = new ConsultaApi();
                Tasa representation = consulta.buscaTasaEnAPi(moneda1, moneda2, cantidad);
                Double tasaDeCambio = representation.conversion_rate();
                Double totalConversion = representation.conversion_result();


                // Registrar la conversión
                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String tiempoRegistro = ahora.format(formatter);

                String registro = String.format("[%s] Se convirtieron %f %s a %s %f", tiempoRegistro, cantidad, moneda1, moneda2, totalConversion);

                String registroGlobal = String.format("[%s]", tiempoRegistro);

                // Crear objeto Json y darle propiedades
                var jsonObject = ObjetoJson.getJsonObject(moneda1, moneda2, tasaDeCambio, cantidad, totalConversion,registroGlobal);
                System.out.println(cantidad +" "+ moneda1 + " x " + tasaDeCambio+ " -> " + moneda2 + " = " + totalConversion);

                // Agregar valores a listas locales y globales de registro
                listaRgistroGlobal.add(registro);
                listaRegistroLocal.add(jsonObject);

                // Generar archivo Json local
                GeneradorDeArchivos archivoJson = new GeneradorDeArchivos();
                archivoJson.guardarJson(listaRegistroLocal);
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }

        //Generar archivos Json Global
        GeneradorDeArchivos archivoRegistro = new GeneradorDeArchivos();
        archivoRegistro.guardarJsonGlobal(listaRgistroGlobal);

    }


}
