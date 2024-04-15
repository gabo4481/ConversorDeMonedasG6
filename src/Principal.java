import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
        JsonArray lista = new JsonArray();
        Scanner lectura = new Scanner(System.in);
        String inicial = "";
        var cambio = "";

        

        while (true) {
            System.out.println("*******************************************************");
            System.out.println("1. USD -> ASR (Dolar a Peso Argentino)");
            System.out.println("2. ASR -> USD (Peso Argentino a Dolar)");
            System.out.println("3. USD -> BOB (Dolar a Boliviano)");
            System.out.println("4. BOB -> USD (Boliviano a Dolar)");
            System.out.println("5. USD -> BRL (Dolar a Real Brasileño)");
            System.out.println("6. BRL -> USD (Real Brasileño a Dolar)");
            System.out.println("7. seleccionar Monedas (Moneda1 -> Moneda2)");
            System.out.println("8. Salir");
            System.out.println("*******************************************************");
            System.out.println("Ingresa tu Eleccion: ");
            var eleccion01 = lectura.nextInt();

            if (eleccion01 == 8){
                break;
            }



            if (eleccion01 == 1) {
                inicial = "USD";
                cambio = "ASR";
            } else if (eleccion01 == 2) {
                inicial = "ASR";
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
            } else if (eleccion01 == 7) {
                System.out.println("Monedas Disponibles: ");
                System.out.println("""
                            AUD\tATS\tBEF\tBRL\tCAD\tCHF\tCNY\tDEM
                            DKK\tESP\tEUR\tFIM\tFRF\tGBP\tGRD\tHKD
                            IEP\tINR\tIRR\tITL\tJPY\tKRW\tLKR\tMXN
                            MYR\tNOK\tNLG\tNZD\tPTE\tSEK\tSGD\tTHB
                            TWD\tUSD\tZAR""");
                System.out.println("**************************************");
                System.out.println("Ingrese su Moneda1 o Nativa: ");
                inicial = lectura.nextLine();
                System.out.println("ingrese su moneda de seleccion: ");
                cambio = lectura.nextLine();


            }


            System.out.println("Ingrese la cantidad a Convertir");
            Double cantidad = lectura.nextDouble();

            ConsultaApi consulta = new ConsultaApi();
            Tasa representation = consulta.buscaTasaEnAPi(inicial , cambio, cantidad);
            String monedaNativa = representation.base_code();
            String monedaCambio = representation.target_code();
            Double tasaDeCambio = representation.conversion_rate();
            Double totalConversion = representation.conversion_result();

            var jsonObject = ObjetoJson.getJsonObject(monedaNativa, monedaCambio, tasaDeCambio, cantidad, totalConversion);
            System.out.println(representation.toString());
            System.out.println(representation.base_code() + " -> " + representation.target_code() + " = " + totalConversion);

            lista.add(jsonObject);

            GeneradorDeArchivos archivoJson = new GeneradorDeArchivos();
            archivoJson.guardarJson(lista);






        }

    }


}
