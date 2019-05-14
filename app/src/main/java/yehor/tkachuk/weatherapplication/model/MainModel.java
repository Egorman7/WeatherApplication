package yehor.tkachuk.weatherapplication.model;

import android.app.Application;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import yehor.tkachuk.weatherapplication.WeatherApp;
import yehor.tkachuk.weatherapplication.api.GeocodingApi;
import yehor.tkachuk.weatherapplication.api.WeatherApi;
import yehor.tkachuk.weatherapplication.model.data.GeocodingDataModel;
import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;

public class MainModel {
    @Inject
    WeatherApi weatherApi;

    @Inject
    GeocodingApi geocodingApi;


    public MainModel(Application application){
       ((WeatherApp) application).getComponent().inject(this);
       //geocoding = new MapsModel(application);
    }

    public void getDailyForecast(double lat, double lon, SingleObserver<WeatherDataModel[]> observer){
        weatherApi.getDailyForecast(lat, lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getHourlyForecast(double lat, double lon, SingleObserver<WeatherDataModel[]> observer){
        weatherApi.getHourlyForecast(lat, lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getGeocoding(String latlng, SingleObserver<GeocodingDataModel> observer){
        geocodingApi.getGeocoding(latlng)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
