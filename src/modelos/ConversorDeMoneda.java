package modelos;

import com.google.gson.Gson;
import consultas.request;

import java.io.IOException;
import java.util.Map;

public class ConversorDeMoneda {

    static request consultas = new request();

    public static float convertirMoneda(String monedaOrigen, String monedaDestino, float cantidad) throws IOException, InterruptedException, IOException {
        Gson gson = new Gson();
        String json = consultas.obtenerconversiones();
        Monedas monedas = gson.fromJson(json, Monedas.class);

        float tasaOrigen = obtenerTasa(monedaOrigen, monedas);
        float tasaDestino = obtenerTasa(monedaDestino, monedas);

        float cantidadEnUSD = cantidad / tasaOrigen;

        float cantidadConvertida = cantidadEnUSD * tasaDestino;

        return cantidadConvertida;
    }

    private static float obtenerTasa(String moneda, Monedas monedas) {

        String monedaUpper = moneda.toUpperCase();


        switch (monedaUpper) {
            case "USD":
                return 1.0f;
            default:
                try {
                    Map<String, Float> conversionRates = monedas.getConversionRates();
                    return conversionRates.getOrDefault(monedaUpper, 0.0f);
                } catch (NullPointerException | IllegalArgumentException e) {
                    throw new IllegalArgumentException("Moneda no encontrada: " + moneda);
                }
        }
    }

}
