package yehor.tkachuk.weatherapplication.model;

import android.app.Application;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yehor.tkachuk.weatherapplication.WeatherApp;
import yehor.tkachuk.weatherapplication.api.GeocodingApi;
import yehor.tkachuk.weatherapplication.api.WeatherApi;
import yehor.tkachuk.weatherapplication.model.data.GeocodingDataModel;
import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;
import yehor.tkachuk.weatherapplication.presenter.callbacks.AppCallback;

public class MainModel {
    @Inject
    WeatherApi weatherApi;

    private MapsModel geocoding;

    public MainModel(Application application){
       ((WeatherApp) application).getComponent().inject(this);
       geocoding = new MapsModel(application);
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
        geocoding.getGeocoding(latlng, observer);
    }
}
