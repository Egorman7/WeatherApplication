package yehor.tkachuk.weatherapplication.api;

import javax.inject.Singleton;

import dagger.Component;
import yehor.tkachuk.weatherapplication.model.MainModel;
import yehor.tkachuk.weatherapplication.model.MapsModel;

@Singleton
@Component(modules = {GeocodingModule.class})
public interface GeocodingComponent {
    void inject(MapsModel mapsModel);
}
