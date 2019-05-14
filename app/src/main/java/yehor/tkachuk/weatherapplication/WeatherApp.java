package yehor.tkachuk.weatherapplication;

import android.app.Application;

import yehor.tkachuk.weatherapplication.api.DaggerNetComponent;
import yehor.tkachuk.weatherapplication.api.NetComponent;
import yehor.tkachuk.weatherapplication.api.NetModule;

public class WeatherApp extends Application {
    private NetComponent component;
    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    public NetComponent getComponent() {
        return component;
    }
}
