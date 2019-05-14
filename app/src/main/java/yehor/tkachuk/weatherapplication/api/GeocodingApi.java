package yehor.tkachuk.weatherapplication.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yehor.tkachuk.weatherapplication.model.data.GeocodingDataModel;

public interface GeocodingApi {
    String PARAMS = "json?key=AIzaSyDUK0oBo4KhYy804Jr3uez8tgn-Dilk9GA";

    @GET(PARAMS)
    Single<GeocodingDataModel> getGeocoding(@Query("latlng") String latlng);
}
