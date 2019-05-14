package yehor.tkachuk.weatherapplication.model;

import android.app.Application;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import yehor.tkachuk.weatherapplication.WeatherApp;
import yehor.tkachuk.weatherapplication.api.GeocodingApi;
import yehor.tkachuk.weatherapplication.model.data.GeocodingDataModel;

public class MapsModel {
    @Inject
    GeocodingApi geocodingApi;

    public MapsModel(Application application){
        ((WeatherApp) application).getGeocoding().inject(this);
    }

    public void getGeocoding(String latlng, SingleObserver<GeocodingDataModel> observer){
        geocodingApi.getGeocoding(latlng)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
