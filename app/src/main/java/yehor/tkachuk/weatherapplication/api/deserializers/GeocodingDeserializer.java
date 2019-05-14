package yehor.tkachuk.weatherapplication.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import yehor.tkachuk.weatherapplication.model.data.GeocodingDataModel;

public class GeocodingDeserializer implements JsonDeserializer<GeocodingDataModel> {
    @Override
    public GeocodingDataModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject address = json.getAsJsonObject().getAsJsonArray("results").get(0).getAsJsonObject();
        String city = address.getAsJsonArray("address_components").get(4).getAsJsonObject().get("long_name").getAsString();
        JsonObject coord = address.getAsJsonObject("geometry").getAsJsonObject("viewport").getAsJsonObject("northeast");
        double lat = coord.get("lat").getAsDouble();
        double lon = coord.get("lon").getAsDouble();
        return new GeocodingDataModel(lat, lon, city);
    }
}
