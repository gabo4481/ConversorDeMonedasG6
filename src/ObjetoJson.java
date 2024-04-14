import com.google.gson.JsonObject;

public class ObjetoJson {
    public static JsonObject getJsonObject(String monedaInicial, String monedaCambio, Double tasaDeCambio,Double cantidad, Double total) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Moneda Nativa", monedaInicial);
        jsonObject.addProperty("Moneda Seleccionada",monedaCambio);
        jsonObject.addProperty("Tasa De Cambio",tasaDeCambio);
        jsonObject.addProperty("Cantidad Seleccionada", cantidad);
        jsonObject.addProperty("Total De Conversion", total);

        return jsonObject;
    }
}
