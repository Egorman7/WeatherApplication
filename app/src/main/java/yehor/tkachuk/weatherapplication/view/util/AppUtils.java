package yehor.tkachuk.weatherapplication.view.util;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.Calendar;
import java.util.Locale;

import yehor.tkachuk.weatherapplication.R;

public class AppUtils {
    public static String getDayName(Calendar c){
        return c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
    }

    public static String getTimeHours(Calendar c){
        return c.get(Calendar.HOUR_OF_DAY)+":00";
    }

    public static String getMonthName(Calendar c){
        return c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public static String getDateString(Calendar c){
        return getDayName(c) + ", " + c.get(Calendar.DAY_OF_MONTH) + " " + getMonthName(c);
    }

    public static Drawable getWeatherImage(Resources resources, String icon){
        switch (icon){
            case "01d":
                return resources.getDrawable(R.drawable.ic_white_day_bright);
            case "01n":
                return resources.getDrawable(R.drawable.ic_white_night_bright);
            case "02d":
                return resources.getDrawable(R.drawable.ic_white_day_cloudy);
            case "02n":
                return resources.getDrawable(R.drawable.ic_white_night_cloudy);
            case "03d":
                return resources.getDrawable(R.drawable.ic_clouds);
            case "03n":
                return resources.getDrawable(R.drawable.ic_clouds);
            case "04d":
                return resources.getDrawable(R.drawable.ic_clouds);
            case "04n":
                return resources.getDrawable(R.drawable.ic_clouds);
            case "09d":
                return resources.getDrawable(R.drawable.ic_white_day_shower);
            case "09n":
                return resources.getDrawable(R.drawable.ic_white_night_shower);
            case "10d":
                return resources.getDrawable(R.drawable.ic_white_day_rain);
            case "10n":
                return resources.getDrawable(R.drawable.ic_white_night_rain);
            case "11d":
                return resources.getDrawable(R.drawable.ic_white_day_thunder);
            case "11n":
                return resources.getDrawable(R.drawable.ic_white_night_thunder);
            case "13d":
                return resources.getDrawable(R.drawable.ic_snowy);
            case "13n":
                return resources.getDrawable(R.drawable.ic_snowy);
            case "50d":
                return resources.getDrawable(R.drawable.ic_mist);
            case "50n":
                return resources.getDrawable(R.drawable.ic_mist);
        }
        return null;
    }

    public static Drawable getWindDirImage(Resources resources, double deg){
        if(deg >= 330 && deg <= 30) return resources.getDrawable(R.drawable.icon_wind_n);
        else if(deg >30 && deg <= 60) return resources.getDrawable(R.drawable.icon_wind_ne);
        else if(deg > 60 && deg <= 120) return resources.getDrawable(R.drawable.icon_wind_e);
        else if(deg >120 && deg <= 150) return resources.getDrawable(R.drawable.icon_wind_se);
        else if(deg > 150 && deg <= 210) return resources.getDrawable(R.drawable.icon_wind_s);
        else if(deg > 210 && deg <= 240) return resources.getDrawable(R.drawable.icon_wind_ws);
        else if(deg > 240 && deg <= 300) return  resources.getDrawable(R.drawable.icon_wind_w);
        else return resources.getDrawable(R.drawable.icon_wind_wn);
    }
}
