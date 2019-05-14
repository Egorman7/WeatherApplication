package yehor.tkachuk.weatherapplication.api;

import javax.inject.Singleton;

import dagger.Component;
import yehor.tkachuk.weatherapplication.model.MainModel;
import yehor.tkachuk.weatherapplication.model.MapsModel;

@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {
    void inject(MainModel mainModel);
    void inject(MapsModel mapsModel);
}
