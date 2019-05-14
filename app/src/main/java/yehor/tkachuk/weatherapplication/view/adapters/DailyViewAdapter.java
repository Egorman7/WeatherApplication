package yehor.tkachuk.weatherapplication.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import yehor.tkachuk.weatherapplication.R;
import yehor.tkachuk.weatherapplication.model.data.WeatherDataModel;
import yehor.tkachuk.weatherapplication.presenter.MainPresenter;
import yehor.tkachuk.weatherapplication.view.util.AppUtils;

public class DailyViewAdapter extends RecyclerView.Adapter<DailyViewAdapter.ViewHolder> {

    private ArrayList<WeatherDataModel> data;
    private final MainPresenter presenter;
    private Context context;

    private int selectedIndex = -1;

    public DailyViewAdapter(@NonNull Context context, @NonNull MainPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void setData(ArrayList<WeatherDataModel> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_forecast_daily, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.day.setText(AppUtils.getDayName(data.get(i).getDatetime()));
        viewHolder.temp.setText(String.format(context.getResources().getString(R.string.temp), data.get(i).getTempMax(), data.get(i).getTempMin()));
        viewHolder.image.setImageDrawable(AppUtils.getWeatherImage(context.getResources(), data.get(i).getIcon()));
        viewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        viewHolder.temp.setTextColor(context.getResources().getColor(R.color.colorBlack));
        viewHolder.day.setTextColor(context.getResources().getColor(R.color.colorBlack));
        viewHolder.image.getDrawable().setTint(context.getResources().getColor(R.color.colorBlack));
        if(i == selectedIndex){
            viewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorLight));
            viewHolder.temp.setTextColor(context.getResources().getColor(R.color.colorBlue));
            viewHolder.day.setTextColor(context.getResources().getColor(R.color.colorBlue));
            viewHolder.image.getDrawable().setTint(context.getResources().getColor(R.color.colorBlue));
        }
        final int index = i;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedIndex != index){
                    int lastIndex = selectedIndex;
                    selectedIndex = index;
                    DailyViewAdapter.this.notifyItemChanged(lastIndex);
                    DailyViewAdapter.this.notifyItemChanged(selectedIndex);
                    presenter.loadHourlyForecast(data.get(selectedIndex).getLat(), data.get(selectedIndex).getLon(), data.get(selectedIndex).getDatetime());
                    presenter.setMainData(data.get(selectedIndex));
                }
            }
        });
    }

    public int getSelectedIndex(){
        return selectedIndex;
    }

    public void selectIndex(int i){
        if(selectedIndex!=i){
            int lastIndex = selectedIndex;
            selectedIndex = i;
            if(lastIndex!=-1) notifyItemChanged(lastIndex);
            notifyItemChanged(selectedIndex);
            presenter.loadHourlyForecast(data.get(selectedIndex).getLat(), data.get(selectedIndex).getLon(), data.get(selectedIndex).getDatetime());
            presenter.setMainData(data.get(selectedIndex));
        }
    }

    @Override
    public int getItemCount() {
        return (data==null) ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView day, temp;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.daily_day);
            temp = itemView.findViewById(R.id.daily_temp);
            image = itemView.findViewById(R.id.daily_image);
        }
    }
}
