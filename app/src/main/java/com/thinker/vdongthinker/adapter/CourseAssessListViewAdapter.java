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
import com.thinker.vdongthinker.bean.CourseAssessBean;

/**
 * Created by zt on 2018/12/13.
 */

public class CourseAssessListViewAdapter extends AdapterBase<CourseAssessBean> {

    public CourseAssessListViewAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AssessViewHolder viewHolder;
        if(null == convertView){
            viewHolder = new AssessViewHolder();
            convertView = inflater.inflate(R.layout.item_assess,null);
            viewHolder.iv_touxiang = convertView.findViewById(R.id.iv_touxiang);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_date = convertView.findViewById(R.id.tv_date);
            viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (AssessViewHolder) convertView.getTag();
        }
        CourseAssessBean bean = (CourseAssessBean) getItem(position);
//        viewHolder.iv_touxiang.setImageResource(bean.getImg());
        Glide.with(context).load(R.mipmap.icon_mall_on).apply(new RequestOptions().circleCrop()).into(viewHolder.iv_touxiang);
        viewHolder.tv_name.setText(bean.getName());
        viewHolder.tv_date.setText(bean.getDate());
        viewHolder.tv_content.setText(bean.getContent());
        return convertView;
    }

    class AssessViewHolder{
        TextView tv_name,tv_date,tv_content;
        ImageView iv_touxiang;
    }
}
