package modelos;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Monedas
{
    private Map<String, Float> conversion_rates;
    public Map<String, Float> getConversionRates() {
        return conversion_rates;
    }


}
