package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * 引导页适配器
 */
public class GuideAdapter extends PagerAdapter {
    private Context context;
    private ImageView[] mScaleViews;
    private int resIds[] = new int[]{};

    public GuideAdapter(Context context, int resIds[]) {
        super();
        this.context = context;
        this.resIds = resIds;
        mScaleViews = new ImageView[resIds.length];
    }

    @Override
    public int getCount() {
        return resIds.length;
    }

    @Override
    /**
     * 获得相应位置上的item(view)
     *
     * @param container是view的容器 其实就是viewpager自己
     * @param position条目的位置
     */
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView scaleView = new ImageView(context);
        scaleView.setImageResource(resIds[position]);
        scaleView.setScaleType(ImageView.ScaleType.FIT_XY);
        mScaleViews[position] = scaleView;
        container.addView(scaleView);
        return scaleView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    /**
     * 销毁不用的条目，系统只保留显示的条目以及左右各一个条目，其他的都销毁
     */
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mScaleViews[position]);
    }

}
