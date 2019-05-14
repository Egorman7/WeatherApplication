package yehor.tkachuk.weatherapplication.api.deserializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;

public class WeatherDataListDeserializer implements JsonDeserializer<WeatherDataModel[]> {
    @Override
    public WeatherDataModel[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(WeatherDataModel.class, new WeatherDataModelDeserializer())
                .create();
//        WeatherDataModel.lat = json.getAsJsonObject().getAsJsonObject("coord").get("lat").getAsDouble();
//        WeatherDataModel.lon = json.getAsJsonObject().getAsJsonObject("coord").get("lon").getAsDouble();
        return gson.fromJson(json.getAsJsonObject().get("list"), WeatherDataModel[].class);
    }
}
