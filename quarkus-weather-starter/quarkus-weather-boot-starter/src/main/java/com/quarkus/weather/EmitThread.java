package com.quarkus.weather;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.Executor;

public class EmitThread implements Executor {
    private String city;
    public EmitThread(String city){
        this.city = city;
    }
    
    @Override
    public void execute(Runnable command) {
        getWeatherList(this.city);
    }

    private String getWeatherList(String city) {
        String url = "http://wthrcdn.etouch.cn/weather_mini?city=" + city;
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        String ret=null;
        try{
            Response response = call.execute();
            ret =  response.body().string();
            System.out.println("查询天气数据：" + ret);
            return ret;
        }catch (IOException e){
            return ret;
        }
    }
}
