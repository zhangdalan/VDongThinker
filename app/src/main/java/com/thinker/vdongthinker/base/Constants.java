package com.thinker.vdongthinker.base;

import com.thinker.vdongthinker.R;

import java.util.List;

/**
 * Created by zt on 2018/12/12.
 */

public class Constants {
    public static int[] course_icon = {R.mipmap.icon_gq,R.mipmap.icon_gz,R.mipmap.icon_hh,R.mipmap.icon_jt,R.mipmap.icon_jzg,R.mipmap.icon_sy,R.mipmap.icon_ws,R.mipmap.icon_wd,R.mipmap.icon_xtq,R.mipmap.icon_qb};
    public static String[] course_name = {"钢琴","古筝","绘画","吉他","架子鼓","声乐","武术","舞蹈","小提琴","全部"};

    public static float alpha = 0;

    public static float getAlpha() {
        return alpha;
    }

    public static void setAlpha(float alpha) {
        Constants.alpha = alpha;
    }
    public static String TEXT_VEDIO_URL = "http://flv2.bn.netease.com/tvmrepo/2016/7/2/V/EBRU4LG2V/SD/EBRU4LG2V-mobile.mp4";
}
