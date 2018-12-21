package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.base.AdapterBase;

/**
 * Created by zt on 2018/12/12.
 */

public class CommunityPhotoGridViewAdapter extends AdapterBase<Integer> {
    private int img_num;
    public CommunityPhotoGridViewAdapter(Context context,int img_num) {
        super(context);
        this.img_num = img_num;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CourseViewHolder viewHolder ;
        if (null == convertView) {
            viewHolder = new CourseViewHolder();
            convertView = inflater.inflate(R.layout.item_photo, null);
            viewHolder.iv_photo = convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_num = convertView.findViewById(R.id.tv_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CourseViewHolder) convertView.getTag();
        }
//        CourseIconBean bean = (CourseIconBean) getItem(position);
        int img = (int) getItem(position);
//        viewHolder.iv_photo.setImageResource(img);
        if (position == getCount()-1){
            viewHolder.tv_num.setVisibility(View.VISIBLE);
            viewHolder.tv_num.setText(img_num+"图");
        }else{
            viewHolder.tv_num.setVisibility(View.GONE);
        }
        return convertView;
    }
    class CourseViewHolder{
        ImageView iv_photo;
        TextView tv_num;
    }
}
