package com.example.weatherapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/*
   "cityid":"101280101",
    "city":"广州",

    "update_time":"2023-03-26 20:08:15",
* */
public class WeatherBean implements Serializable {
    @SerializedName("cityid")
    private String cityId;

    @SerializedName("city")
    private String city;

    @SerializedName("update_time")
    private String updateTime;

    @SerializedName("data")
    private List<DayWeatherBean> dayWeathers;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<DayWeatherBean> getDayWeathers() {
        return dayWeathers;
    }

    public void setDayWeathers(List<DayWeatherBean> dayWeathers) {
        this.dayWeathers = dayWeathers;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "cityId='" + cityId + '\'' +
                ", city='" + city + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", dayWeathers=" + dayWeathers +
                '}';
    }
}
