package com.thinker.vdongthinker.tool;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.thinker.vdongthinker.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-04-14  13:52
 */

public class GlideImageLoader extends ImageLoader {
    private boolean isCorner;
    public GlideImageLoader(boolean isCorner) {
        this.isCorner = isCorner;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        //Glide 加载图片简单用法
//        Glide.with(context).load(((BannerImage) path).getImgPath()).into(imageView);
//        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(6)).placeholder(R.mipmap.img_defult_mall);
        int radius = 0;
        if (isCorner){
            radius = 10;
        }
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.mipmap.img_defult_mall).priority(Priority.HIGH).transform(new GlideRoundTransform(radius, GlideRoundTransform.CornerType.ALL));
            Glide.with(context).load(path).apply(options).into(imageView);

//        Uri uri = Uri.parse(((BannerImage) path).getImgPath());
//        imageView.setImageURI(uri);
    }
}
