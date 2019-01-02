package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.AdapterBase;
import com.thinker.vdongthinker.bean.CourseIconBean;

/**
 * Created by zt on 2018/12/12.
 */

public class CoursePhotoGridViewAdapter extends AdapterBase<String> {
    private Context mContext;
    public CoursePhotoGridViewAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CourseViewHolder viewHolder ;
        if (null == convertView) {
            viewHolder = new CourseViewHolder();
            convertView = inflater.inflate(R.layout.item_photo, null);
            viewHolder.iv_photo = convertView.findViewById(R.id.iv_photo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CourseViewHolder) convertView.getTag();
        }
//        CourseIconBean bean = (CourseIconBean) getItem(position);
        String img = (String) getItem(position);
        Glide.with(mContext).load(img).into(viewHolder.iv_photo);
        return convertView;
    }
    class CourseViewHolder{
        ImageView iv_photo;
    }
}
