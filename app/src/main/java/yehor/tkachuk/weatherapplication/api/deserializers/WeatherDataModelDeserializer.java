package yehor.tkachuk.weatherapplication.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;

public class WeatherDataModelDeserializer implements JsonDeserializer<WeatherDataModel> {
    @Override
    public WeatherDataModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        long date = jsonObject.get("dt").getAsLong();
        double tempMin, tempMax, wind, windDeg;
        int humidity;
        if(jsonObject.has("main")) {
            tempMin = jsonObject.getAsJsonObject("main").get("temp_min").getAsDouble();
            tempMax = jsonObject.getAsJsonObject("main").get("temp_max").getAsDouble();
            humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
            wind = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
            windDeg = jsonObject.getAsJsonObject("wind").get("deg").getAsDouble();
        } else {
            tempMin = jsonObject.getAsJsonObject("temp").get("min").getAsDouble();
            tempMax = jsonObject.getAsJsonObject("temp").get("max").getAsDouble();
            humidity = jsonObject.get("humidity").getAsInt();
            wind = jsonObject.get("speed").getAsDouble();
            windDeg = jsonObject.get("deg").getAsInt();
        }
        //Log.d("DATA", jsonObject.get("weather").toString());
        String icon = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("icon").getAsString();
        return new WeatherDataModel(date*1000, tempMin, tempMax, wind, windDeg, humidity, icon);
    }
}
