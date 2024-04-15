import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivos {

    public void guardarJson(Object jsonArray) throws IOException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        FileWriter escritura = new FileWriter("HistorialDeConversiones.json");
        escritura.write(gson.toJson(jsonArray));
        escritura.close();
    }
}
