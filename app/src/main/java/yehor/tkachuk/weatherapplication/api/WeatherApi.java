package yehor.tkachuk.weatherapplication.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;

public interface WeatherApi {
    String PARAMS = "?units=metric&APPID=03705d03c452bc971b99acedea642e54";

    @GET("forecast/hourly" + PARAMS)
    Single<WeatherDataModel[]> getHourlyForecast(@Query("lat") double lat, @Query("lon") double lon);

    @GET("forecast/daily" + PARAMS)
    Single<WeatherDataModel[]> getDailyForecast(@Query("lat") double lat, @Query("lon") double lon);
}
