package com.thinker.vdongthinker.tool.http;

import com.thinker.vdongthinker.base.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpService {

    private static final long TIMEOUT = 30;
    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
        //添加通用的Header
        .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder() ;
                /*替换成为自己的token*/
                builder.addHeader("Connection" , "close") ;
                return chain.proceed(builder.build());
            }
        })
            /*添加一个数据拦截器，因为所有的http请求都是Retrofit封装好的，除了问题在这里拦截调试*/
        .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

            }
        }).setLevel(HttpLoggingInterceptor.Level.BASIC))
          .connectTimeout(TIMEOUT , TimeUnit.SECONDS)
          .readTimeout(TIMEOUT ,TimeUnit.SECONDS)
          .build();

    private static RetrofitService retrofitService = new Retrofit.Builder()
            .baseUrl(Constants.HostURL)
            .addConverterFactory(StringConverterFactory.create()) //不用gson转换器，自定义转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   //添加Retrofit到RXjava的转换器
            .client(httpClient)
            .build()
            .create(RetrofitService.class) ;


    public static RetrofitService getInstance(){
        return retrofitService ;
    }
}