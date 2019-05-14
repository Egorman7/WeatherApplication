package yehor.tkachuk.weatherapplication.model.data;

public class GeocodingDataModel {
    double lat, lon;
    String city;

    public GeocodingDataModel(double lat, double lon, String city) {
        this.lat = lat;
        this.lon = lon;
        this.city = city;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getCity() {
        return city;
    }
}
