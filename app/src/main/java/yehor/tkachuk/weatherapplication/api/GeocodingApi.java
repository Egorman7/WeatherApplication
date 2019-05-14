package yehor.tkachuk.weatherapplication.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yehor.tkachuk.weatherapplication.model.data.GeocodingDataModel;

public interface GeocodingApi {
    String PARAMS = "?language=ru&result_type=locality|administrative_area_level_1&key=AIzaSyAuIBEaMQrOm6lFzXfzR_3_yrZfM8vCMig";

    @GET("geocode/json?"+PARAMS)
    Single<GeocodingDataModel> getGeocoding(@Query("latlng") String latlng);
}
