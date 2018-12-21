//package com.thinker.vdongthinker.adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v4.view.PagerAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.bumptech.glide.Glide;
//
//import java.util.List;
//
///**
// * Created by zt on 2018/12/18.
// */
//
//public class ImageViewPagerAdapter extends PagerAdapter {
//    List<String> imgs;
//    LayoutInflater inflater;
//    Context context;
//    boolean isSuccessLoad = true;//图片是否加载成功
//    public ImageViewPagerAdapter(List<String> imgs,Context context){
//        this.imgs = imgs;
//        inflater = LayoutInflater.from(context);
//        this.context = context;
//    }
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return imgs.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View arg0, Object arg1) {
//        // TODO Auto-generated method stub
//        return arg0 == arg1;
//    }
//    //TODO 这里返回一个视图实现基本功能
//    @Override
//    public Object instantiateItem(final ViewGroup container, int position) {
//
//        final String url = imgs.get(position);
//        final PhotoView photoView = new PhotoView(context);
////        Glide.with(context)
////                .load(url)
////                .listener(new RequestListener<String, GlideDrawable>() {
////                    @Override
////                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
////                        isSuccessLoad = false;
////                        return false;
////                    }
////
////                    @Override
////                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
////                        isSuccessLoad = true;
////                        return false;
////                    }
////                })
////                .transform(new GlideRoundTransform(context,5))
////                .placeholder(R.drawable.pic_loading)
////                .error(R.drawable.pic_load_error)
////                .into(photoView);
//        container.addView(photoView);
//        photoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(isSuccessLoad){
//                    ((ProjectInfoImageActivity)context).finish();
//                    ((ProjectInfoImageActivity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                }else{
//
//                    Glide.with(context)
//                            .load(url)
//                            .transform(new GlideRoundTransform(context,5))
//                            .placeholder(R.drawable.pic_loading)
//                            .error(R.drawable.pic_load_error)
//                            .into(photoView);
//                    Log.i("浏览大图","点击重新加载");
//                }
//            }
//        });
//        return photoView;
//
//    }
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View)object);
//    }
//}
