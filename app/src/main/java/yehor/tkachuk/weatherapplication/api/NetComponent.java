package yehor.tkachuk.weatherapplication.api;

import javax.inject.Singleton;

import dagger.Component;
import yehor.tkachuk.weatherapplication.model.MainModel;

@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {
    void inject(MainModel mainModel);
}
