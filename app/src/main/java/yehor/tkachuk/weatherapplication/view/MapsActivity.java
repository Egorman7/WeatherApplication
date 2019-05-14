package yehor.tkachuk.weatherapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.Locale;

import yehor.tkachuk.weatherapplication.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AutocompleteSupportFragment mAutocomplete;

    private LatLng mLocation;
    private int resultCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mLocation = new LatLng(getIntent().getDoubleExtra("lat", 0), getIntent().getDoubleExtra("lon",0));

        ImageButton button = findViewById(R.id.backButton);
        button.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));
        button.getDrawable().setTint(getResources().getColor(R.color.colorWhite));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(mLocation).title("Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mLocation));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                setMapMarker(latLng);
            }
        });
        initLiveSearch();
    }

    private void setMapMarker(LatLng latLng){
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title("Location"));
        mLocation = latLng;
        resultCode = 1;
    }

    private void initLiveSearch(){
        Places.initialize(this, getResources().getString(R.string.google_maps_key), Locale.getDefault());
        mAutocomplete = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        mAutocomplete.setPlaceFields(Arrays.asList(Place.Field.ID ,Place.Field.NAME, Place.Field.LAT_LNG));
        mAutocomplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                Log.d("PLACE", place.getLatLng().toString());
                if(place.getLatLng()!=null)
                    setMapMarker(place.getLatLng());
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.e("LiveSearch", status.getStatusMessage());
            }
        });
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("lat", mLocation.latitude);
        intent.putExtra("lon", mLocation.longitude);
        setResult(resultCode, intent);
        super.finish();
    }
}
