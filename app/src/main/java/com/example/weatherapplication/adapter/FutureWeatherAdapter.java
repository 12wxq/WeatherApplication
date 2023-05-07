package com.example.weatherapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapplication.R;
import com.example.weatherapplication.bean.DayWeatherBean;

import java.util.List;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.WeatherViewHolder> {
    private Context mContext;
    private List<DayWeatherBean> mWeatherBeans;

    public FutureWeatherAdapter(Context mContext,List<DayWeatherBean> weatherBeans) {
        this.mContext = mContext;
        this.mWeatherBeans=weatherBeans;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_item_layout, parent, false);
      WeatherViewHolder weatherViewHolder= new WeatherViewHolder(view);

        return weatherViewHolder;
    }

    @Override
    public int getItemCount() {
        return (mWeatherBeans==null)?0:mWeatherBeans.size();//显示多少项
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        DayWeatherBean WeatherBean = mWeatherBeans.get(position);
        holder.tvWeather.setText(WeatherBean.getWea());
        holder.tvTem.setText(WeatherBean.getTem());
        holder.tvTemLowHigh.setText(WeatherBean.getTem2()+"~"+WeatherBean.getTem1());
        holder.tvWin.setText(WeatherBean.getWin()[0]+WeatherBean.getWinSpeed());
        holder.tvAir.setText("空气质量:"+WeatherBean.getAir()+WeatherBean.getAirLevel());
        holder.ivWeather.setImageResource(getImgResOfWeather(WeatherBean.getWeaImg()));
    }
    private int getImgResOfWeather(String Weastr){
        //根据天气显示对应的图标
        int result=0;
        switch (Weastr){
            case "qing":
                result=R.drawable.biz_plugin_weather_qing;
                break;
            case "yin":
                result=R.drawable.biz_plugin_weather_yin;
                break;
            case "yu":
                result=R.drawable.biz_plugin_weather_dayu;
                break;
            case "yun":
                result=R.drawable.biz_plugin_weather_duoyun;
                break;
            case "bingbao":
                result=R.drawable.biz_plugin_weather_leizhenyubingbao;
                break;
            case "wu":
                result=R.drawable.biz_plugin_weather_wu;
                break;
            case "shachen":
                result=R.drawable.biz_plugin_weather_shachenbao;
                break;
            case "lei":
                result=R.drawable.biz_plugin_weather_leizhenyu;
                break;
            case "xue":
                result=R.drawable.biz_plugin_weather_daxue;
                break;
            default:

                result=R.drawable.biz_plugin_weather_qing;

                break;
        }
        return result;
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder{
        TextView tvWeather,tvTem,tvTemLowHigh,tvWin,tvAir;
        ImageView ivWeather;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeather=itemView.findViewById(R.id.tv_weather);
            tvAir=itemView.findViewById(R.id.tv_air);
            tvTem=itemView.findViewById(R.id.tv_tem);
            tvTemLowHigh=itemView.findViewById(R.id.tv_tem_low_high);
            tvWin=itemView.findViewById(R.id.tv_win);
            ivWeather=itemView.findViewById(R.id.iv_weather);
        }
    }
}
