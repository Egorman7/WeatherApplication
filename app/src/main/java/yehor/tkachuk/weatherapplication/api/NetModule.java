package yehor.tkachuk.weatherapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yehor.tkachuk.weatherapplication.api.deserializers.GeocodingDeserializer;
import yehor.tkachuk.weatherapplication.api.deserializers.WeatherDataListDeserializer;
import yehor.tkachuk.weatherapplication.api.deserializers.WeatherDataModelDeserializer;
import yehor.tkachuk.weatherapplication.model.data.GeocodingDataModel;
import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;

@Module
public class NetModule {
    public static final String BASE_URL_WEATHER = "http://api.openweathermap.org/data/2.5/";
    public static final String BASE_URL_GEOCODING = "https://maps.googleapis.com/maps/api/";

    @Provides
    @Singleton
    WeatherApi provideWeatherApi(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(WeatherDataModel.class, new WeatherDataModelDeserializer())
                .registerTypeAdapter(WeatherDataModel[].class, new WeatherDataListDeserializer())
                .create();
        Retrofit retrofit = new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL_WEATHER)
                .build();
        return retrofit
                .create(WeatherApi.class);
    }

    @Provides
    @Singleton
    GeocodingApi provideGeocodingApi(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(GeocodingDataModel.class, new GeocodingDeserializer())
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_GEOCODING)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GeocodingApi.class);
    }
}
