import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {

        JsonArray lista = new JsonArray();

        Scanner lectura = new Scanner(System.in);

        System.out.println("ingrea tu moneda");
        var tasaInicial = lectura.nextLine();

        System.out.println("ingresa tu moneda de cambio");
        var tasaCambio = lectura.nextLine();

        System.out.println("ingresa cantidad a cambiar: ");
        var cantidad = lectura.nextDouble();

        ConsultaApi consulta = new ConsultaApi();
        var representation = consulta.buscaTasaEnAPi(tasaInicial, tasaCambio);
        double conversion = representation.conversion(cantidad,representation.conversion_rate());
        var jsonObject = ObjetoJson.getJsonObject(representation.base_code(), representation.target_code(), representation.conversion_rate(), cantidad,conversion);

        System.out.println(representation.base_code() + " -> " + representation.target_code() + " = "+conversion );

        lista.add(jsonObject);

        GeneradorDeArchivos archivoJson = new GeneradorDeArchivos();
        archivoJson.guardarJson(lista);



    }


}
