package yehor.tkachuk.weatherapplication.model.data;

import java.util.Calendar;

public class WeatherDataModel {
    Calendar datetime;
    double tempMin, tempMax, wind, windDeg;
    int humidity;
    String icon;
    public static double lat, lon;
    public static String city;

    public WeatherDataModel(long datetime, double tempMin, double tempMax, double wind, double windDeg, int humidity, String icon) {
        this.datetime = Calendar.getInstance();
        this.datetime.setTimeInMillis(datetime);
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.wind = wind;
        this.windDeg = windDeg;
        this.humidity = humidity;
        this.icon = icon;
    }

    public static String getCity() {
        return city;
    }

    public Calendar getDatetime() {
        return datetime;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getWind() {
        return wind;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getIcon() {
        return icon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
