package yehor.tkachuk.weatherapplication.view.interfaces;

import java.util.ArrayList;

import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;

public interface IMainView {
    void showToast(String text);
    void setDailyData(ArrayList<WeatherDataModel> data);
    void setHourlyData(ArrayList<WeatherDataModel> data);
    void setMainData(WeatherDataModel data);
    void setCityName(String cityName);
}
