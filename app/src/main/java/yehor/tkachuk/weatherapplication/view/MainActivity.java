package yehor.tkachuk.weatherapplication.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import yehor.tkachuk.weatherapplication.R;
import yehor.tkachuk.weatherapplication.model.MainModel;
import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;
import yehor.tkachuk.weatherapplication.presenter.MainPresenter;
import yehor.tkachuk.weatherapplication.view.adapters.DailyViewAdapter;
import yehor.tkachuk.weatherapplication.view.adapters.HourlyViewAdapter;
import yehor.tkachuk.weatherapplication.view.interfaces.IMainView;
import yehor.tkachuk.weatherapplication.view.util.AppUtils;

public class MainActivity extends AppCompatActivity implements IMainView {

    private MainPresenter mPresenter;

    private Toolbar mToolbar;
    private RecyclerView mHourlyView, mDailyView;
    private TextView mDateText, mTempText, mHumText, mWindText, mCityName;
    private ImageView mImage;

    private DailyViewAdapter mDailyAdapter;
    private HourlyViewAdapter mHourlyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(new MainModel(getApplication()), this);
        initView();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadLocation();
    }

    private void initView(){
        mToolbar = findViewById(R.id.toolbar);
        mHourlyView = findViewById(R.id.listHoulyForecast);
        mDailyView = findViewById(R.id.litDaysForecast);
        mDateText = findViewById(R.id.textCurrentDate);
        mTempText = findViewById(R.id.textCurrentTemperature);
        mHumText = findViewById(R.id.textCurrentHumidity);
        mWindText = findViewById(R.id.textCurrentWind);
        mCityName = findViewById(R.id.toolbarCityName);
        mImage = findViewById(R.id.currentImage);
        mHourlyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mDailyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDailyAdapter = new DailyViewAdapter(this, mPresenter);
        mDailyView.setAdapter(mDailyAdapter);
        mHourlyAdapter = new HourlyViewAdapter(this);
        mHourlyView.setAdapter(mHourlyAdapter);
    }

    private void loadLocation(){
        LocationManager loc = (LocationManager) getSystemService(LOCATION_SERVICE);
        try{
            if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
            loc.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, createPendingResult(1, new Intent(), 0));
            Location l = loc.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            LatLng here = new LatLng(l.getLatitude(), l.getLongitude());
            WeatherDataModel.lat = here.latitude; WeatherDataModel.lon = here.longitude;
            mPresenter.loadDailyForecast(WeatherDataModel.lat, WeatherDataModel.lon);
        } catch (SecurityException ignored){ }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_map) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("lat", WeatherDataModel.lat);
            intent.putExtra("lon", WeatherDataModel.lon);
            startActivityForResult(intent, 2);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            if(resultCode == 1){
                WeatherDataModel.lat = data.getDoubleExtra("lat", 0);
                WeatherDataModel.lon = data.getDoubleExtra("lon", 0);
                mPresenter.loadDailyForecast(WeatherDataModel.lat, WeatherDataModel.lon);
            }
        }
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDailyData(ArrayList<WeatherDataModel> data) {
        mDailyAdapter.setData(data);
        mDailyAdapter.selectIndex(0);
    }

    @Override
    public void setHourlyData(ArrayList<WeatherDataModel> data) {
        mHourlyAdapter.setData(data);
    }

    @Override
    public void setMainData(WeatherDataModel data) {
        mDateText.setText(AppUtils.getDateString(data.getDatetime()));
        mTempText.setText(getResources().getString(R.string.temp, data.getTempMax(), data.getTempMin()));
        mHumText.setText(getResources().getString(R.string.humidity, data.getHumidity()));
        mWindText.setText(getResources().getString(R.string.wind, data.getWind()));
        mWindText.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.drawable.icon_wind), null, AppUtils.getWindDirImage(getResources(), data.getWindDeg()), null);
        mImage.setImageDrawable(AppUtils.getWeatherImage(getResources(), data.getIcon()));
        mImage.getDrawable().setTint(getResources().getColor(R.color.colorWhite));
    }

    @Override
    public void setCityName(String cityName) {
        mCityName.setText(cityName);
    }
}
