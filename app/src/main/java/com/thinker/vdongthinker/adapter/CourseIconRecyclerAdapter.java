package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.bean.CourseIconBean;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;

/**
 * Created by zt on 2018/12/10.
 */

public class CourseIconRecyclerAdapter extends BaseAdapterRecycler<CourseIconBean> {

    private Context mContext;
//    private List<IndexMallRecyclerBean> listMall;
    private RecyclerView.LayoutManager layoutManager;

    public CourseIconRecyclerAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
        this.mContext = context;
        recyclerView.addItemDecoration(new IndexRecyclerItemDecoration(50,30));
        layoutManager = recyclerView.getLayoutManager();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_icon,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CourseIconBean bean = (CourseIconBean) getItem(position);
        final CourseViewHolder viewHolder = (CourseViewHolder) holder;
        viewHolder.tv_name.setText(bean.getName());
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(10)).placeholder(bean.getUrl());
        Glide.with(mContext).load("").apply(options).into(viewHolder.iv_icon);
        viewHolder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onItemClick(v,position);
            }
        });
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_name;
        private LinearLayout ll_item;
        public CourseViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            ll_item = itemView.findViewById(R.id.ll_item);
        }
    }

}
