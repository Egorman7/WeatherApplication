package yehor.tkachuk.weatherapplication.presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import yehor.tkachuk.weatherapplication.model.MainModel;
import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;
import yehor.tkachuk.weatherapplication.view.interfaces.IMainView;

public class MainPresenter {
    private MainModel model;
    private IMainView view;

    public MainPresenter(MainModel model, IMainView view) {
        this.model = model;
        this.view = view;
    }

    public void loadDailyForecast(double lat, double lon){
        model.getDailyForecast(lat, lon, new SingleObserver<WeatherDataModel[]>() {
            @Override
            public void onSubscribe(Disposable d) {
                view.showToast("Loading data...");
            }

            @Override
            public void onSuccess(WeatherDataModel[] weatherDataModels) {
                view.setDailyData(new ArrayList<>(Arrays.asList(weatherDataModels)));
                view.setCityName(WeatherDataModel.getCity());
            }

            @Override
            public void onError(Throwable e) {
                view.showToast(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void loadHourlyForecast(double lat, double lon, final Calendar c){
        model.getHourlyForecast(lat, lon, new SingleObserver<WeatherDataModel[]>() {
            @Override
            public void onSubscribe(Disposable d) {
                view.showToast("Loading hourly forecast...");
            }

            @Override
            public void onSuccess(WeatherDataModel[] weatherDataModels) {
                ArrayList<WeatherDataModel> list = new ArrayList<>();
                for(int i=0; i<weatherDataModels.length; i++){
                    if(weatherDataModels[i].getDatetime().get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)){
                        list.add(weatherDataModels[i]);
                    }
                }
                view.setHourlyData(list);
            }

            @Override
            public void onError(Throwable e) {
                view.showToast(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void setMainData(WeatherDataModel data){
        view.setMainData(data);
    }
}
