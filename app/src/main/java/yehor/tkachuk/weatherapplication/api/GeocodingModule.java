package yehor.tkachuk.weatherapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GeocodingModule {
    public static final String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/";

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        Gson gson = new GsonBuilder()
                .create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    GeocodingApi provideGeocodingApi(Retrofit retrofit){
        return retrofit.create(GeocodingApi.class);
    }
}
