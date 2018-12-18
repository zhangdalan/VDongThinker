package com.thinker.vdongthinker.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class NetUtils {
    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isAvailable();
    }

    /**
     * 换算 强制按东八区计算
     *
     * @param miao
     * @return
     */
    public static String getDetailTime(long miao) {
//        long temp = Long.parseLong(miao);
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        Locale loc = new Locale("zh", "CN");
        Date d = new Date(miao);
        // 设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", loc);
        df.setTimeZone(tz);
        // 格式化输出
        return df.format(d);
    }

    /**
     * 换算 强制按东八区计算
     *
     * @param
     * @return
     */
    public static String getTime() {
//        long temp = Long.parseLong(miao);
        Date d = new Date();
        // 设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        // 格式化输出
        return df.format(d);
    }
    }