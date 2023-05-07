package com.example.weatherapplication.uitl;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {
  //https://tianqiapi.com/api?version=v1&appid=36646344&appsecret=c1lgQbP9
    //https://www.yiketianqi.com/free/week?unescape=1&appid=42287898&appsecret=JXMEJeL8
    public static final String URL_weather_with_future="https://tianqiapi.com/api?version=v1&appid=36646344&appsecret=c1lgQbP9";
    public static String doGet(String urlStr) throws MalformedURLException {
        String result="";
        //1连接网络http
        HttpURLConnection connection=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        try {
            URL url = new URL(urlStr);
             connection =(HttpURLConnection) url.openConnection();
             connection.setRequestMethod("GET");
             connection.setConnectTimeout(5000);
             //从连接中读取数据（二进制）
            InputStream inputStream=connection.getInputStream();
           inputStreamReader=new InputStreamReader(inputStream);
           //二进制流送入缓冲区
           bufferedReader=new BufferedReader(inputStreamReader);//不可能一下子读出来，缓冲区
           // String line = bufferedReader.readLine();
            //从缓冲区一行一行读字符串
            String line = "";
            StringBuilder stringBuilder=new StringBuilder();
            while ((line= bufferedReader.readLine())!=null){
               stringBuilder.append(line);
            }
            result=stringBuilder.toString();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if (inputStreamReader!=null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        return  result;
    }
    public static String getWeatherOfCity(String city) throws MalformedURLException {
        String result="";
        //拼接出天气的URL
        //https://www.yiketianqi.com/free/week?unescape=1&appid=42287898&appsecret=JXMEJeL8
        String weatherUrl=URL_weather_with_future+"&city="+city;
        Log.d("fan", "----weatherUrl----"+weatherUrl);
        String weatherResult=doGet(weatherUrl);
        Log.d("fan", "----weatherUrl----"+weatherResult);
        return weatherResult;
    }
}
