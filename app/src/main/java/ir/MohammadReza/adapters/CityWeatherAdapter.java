package ir.MohammadReza.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.MohammadReza.R;
import ir.MohammadReza.applications.application;
import ir.MohammadReza.interfaces.onSwipeListener;
import ir.MohammadReza.Objects.CityWeather;
import ir.MohammadReza.utils.IconProvider;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;



public class CityWeatherAdapter extends RecyclerView.Adapter<CityWeatherAdapter.ViewHolder> implements onSwipeListener {
    private List<CityWeather> cities;
    private int layoutReference;
    private OnItemClickListener onItemClickListener;
    private Activity activity;
    private View parentView;


    public CityWeatherAdapter(List<CityWeather> cities, int layoutReference,  Activity activity,OnItemClickListener onItemClickListener) {
        this.cities = cities;
        this.layoutReference = layoutReference;
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentView = parent;
        View view = LayoutInflater.from(activity).inflate(layoutReference,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(cities.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    @Override
    public void onItemDelete(final int position) {
        CityWeather tempCity = cities.get(position);
        cities.remove(position);
        notifyItemRemoved(position);

        Snackbar.make(parentView, "Removed", Snackbar.LENGTH_LONG)
                .setAction("Undo", v -> {
                    addItem(position, tempCity);
                    //new MainActivity().recyclerScrollTo(position);
                }).show();

    }
    public void addItem(int position, CityWeather city) {
        cities.add(position, city);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewCardCityName) TextView textViewCityName;
        @BindView(R.id.textViewCardWeatherDescription) TextView textViewWeatherDescription;
        @BindView(R.id.textViewCardCurrentTemp) TextView textViewCurrentTemp;
        @BindView(R.id.textViewCardMaxTemp) TextView textViewMaxTemp;
        @BindView(R.id.textViewCardMinTemp) TextView  textViewMinTemp;
        @BindView(R.id.imageViewCardWeatherIcon)
        GifImageView imageViewWeatherIcon;
        @BindView(R.id.cardViewWeatherCard)
        CardView cardViewWeather;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void bind(final CityWeather cityWeather, final OnItemClickListener onItemClickListener){
            textViewCityName.setText(cityWeather.getCity().getName()+", "+cityWeather.getCity().getCountry());
            textViewWeatherDescription.setText(cityWeather.getWeeklyWeather().get(0).getWeatherDetails().get(0).getLongDescription());
            textViewCurrentTemp.setText((int) cityWeather.getWeeklyWeather().get(0).getTemp().getDay()+"°");
            textViewMaxTemp.setText((int) cityWeather.getWeeklyWeather().get(0).getTemp().getMax()+"°");
            textViewMinTemp.setText((int) cityWeather.getWeeklyWeather().get(0).getTemp().getMin()+"°");
            String weatherDescription = cityWeather.getWeeklyWeather().get(0).getWeatherDetails().get(0).getShotDescription();
//            Picasso.with(activity).load(IconProvider.getImageIcon(weatherDescription)).into(imageViewWeatherIcon);
            try {
                imageViewWeatherIcon.setImageDrawable(new GifDrawable(application.getContext().getResources(), IconProvider.getImageIcon(weatherDescription)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            cardViewWeather.setOnClickListener(view -> onItemClickListener.onItemClick(cityWeather,getAdapterPosition(), cardViewWeather));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CityWeather cityWeather, int position, View view);
    }

}
