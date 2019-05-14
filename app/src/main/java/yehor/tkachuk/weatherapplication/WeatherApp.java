package yehor.tkachuk.weatherapplication;

import android.app.Application;

import yehor.tkachuk.weatherapplication.api.DaggerGeocodingComponent;
import yehor.tkachuk.weatherapplication.api.DaggerNetComponent;
import yehor.tkachuk.weatherapplication.api.GeocodingComponent;
import yehor.tkachuk.weatherapplication.api.GeocodingModule;
import yehor.tkachuk.weatherapplication.api.NetComponent;
import yehor.tkachuk.weatherapplication.api.NetModule;

public class WeatherApp extends Application {
    private NetComponent component;
    private GeocodingComponent geocoding;
    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();

        geocoding = DaggerGeocodingComponent.builder()
                .geocodingModule(new GeocodingModule())
                .build();
    }

    public NetComponent getComponent() {
        return component;
    }

    public GeocodingComponent getGeocoding(){return geocoding;}
}
