package yehor.tkachuk.weatherapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yehor.tkachuk.weatherapplication.api.deserializers.WeatherDataListDeserializer;
import yehor.tkachuk.weatherapplication.api.deserializers.WeatherDataModelDeserializer;
import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;

@Module
public class NetModule {
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(WeatherDataModel.class, new WeatherDataModelDeserializer())
                .registerTypeAdapter(WeatherDataModel[].class, new WeatherDataListDeserializer())
                .create();
        return new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    WeatherApi provideWeatherApi(Retrofit retrofit){
        return retrofit
                .create(WeatherApi.class);
    }
}
