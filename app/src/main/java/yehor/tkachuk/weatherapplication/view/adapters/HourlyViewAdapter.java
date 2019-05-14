package yehor.tkachuk.weatherapplication.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import yehor.tkachuk.weatherapplication.R;
import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;
import yehor.tkachuk.weatherapplication.view.util.AppUtils;

public class HourlyViewAdapter extends RecyclerView.Adapter<HourlyViewAdapter.ViewHolder> {

    private ArrayList<WeatherDataModel> data;
    private Context context;

    public HourlyViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<WeatherDataModel> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_forecast_hourly, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.time.setText(Html.fromHtml(AppUtils.getTimeHours(data.get(i).getDatetime())));
        viewHolder.temp.setText(String.format(context.getResources().getString(R.string.temp), data.get(i).getTempMax(), data.get(i).getTempMin()));
        viewHolder.temp.setCompoundDrawablesWithIntrinsicBounds(null, AppUtils.getWeatherImage(context.getResources(), data.get(i).getIcon()), null, null);
        viewHolder.temp.getCompoundDrawables()[1].setTint(context.getResources().getColor(R.color.colorWhite));
    }

    @Override
    public int getItemCount() {
        return (data==null) ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView time, temp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.hourly_time);
            temp = itemView.findViewById(R.id.hourly_temp);
        }
    }
}
