package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

/**
 * Created by zjw on 2018/12/25.
 */
public class CommunityPhotoViewAdapter extends PagerAdapter {
    List<String> imgs;
    LayoutInflater inflater;
    Context context;
    boolean isSuccessLoad = true;//图片是否加载成功
    public CommunityPhotoViewAdapter(List<String> imgs,Context context){
        this.imgs = imgs;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }
    //TODO 这里返回一个视图实现基本功能
    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

        final String url = imgs.get(position);
        final PhotoView photoView = new PhotoView(context);
        Glide.with(context)
                .load(url)
                .into(photoView);
        container.addView(photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    (context).finish();
//                    ( context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        return photoView;

    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
