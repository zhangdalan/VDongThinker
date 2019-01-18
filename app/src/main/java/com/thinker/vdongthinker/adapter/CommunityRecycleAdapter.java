package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.bean.CommunityBean;

import java.util.ArrayList;


/**
 * Created by zjw on 2018/12/17.
 */

public class CommunityRecycleAdapter extends BaseAdapterRecycler<CommunityBean> {
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    private ArrayList<String> list_photo;
    private CoursePhotoGridViewAdapter adapter_gv;

    public CommunityRecycleAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
        this.mContext = context;
        layoutManager = recyclerView.getLayoutManager();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommunityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CommunityViewHolder viewHolder = (CommunityViewHolder) holder;
        CommunityBean bean = (CommunityBean) getItem(position);
        viewHolder.tv_name.setText(bean.getName());
        viewHolder.tv_type.setText(bean.getType());
        viewHolder.tv_content.setText(bean.getContent());
        viewHolder.tv_date.setText(bean.getDate());
        viewHolder.tv_assess_num.setText(bean.getAssess_num()+"");
        viewHolder.tv_zan_num.setText(bean.getLike()+"");
        if(bean.getIsVideo() == 1){
            viewHolder.rv_video.setVisibility(View.VISIBLE);
            viewHolder.gv_photo.setVisibility(View.GONE);
        }else{
            viewHolder.rv_video.setVisibility(View.GONE);
            viewHolder.gv_photo.setVisibility(View.VISIBLE);

            list_photo = (ArrayList<String>) bean.getImgs();
            adapter_gv = new CoursePhotoGridViewAdapter(mContext);
            adapter_gv.setItems(list_photo);
            viewHolder.gv_photo.setAdapter(adapter_gv);
            viewHolder.gv_photo.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
        viewHolder.tv_zan_num.setText(bean.getLike()+"");
        Glide.with(mContext).load(R.mipmap.icon_mall_on).apply(new RequestOptions().circleCrop()).into(viewHolder.iv_touxiang);
        viewHolder.rv_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommunityClickListener.onVideoClick(position);
            }
        });
        viewHolder.ll_assess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.ll_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommunityClickListener.onZanClick(position);
            }
        });
        viewHolder.gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onCommunityClickListener.onGridViewClick(position);
            }
        });
        viewHolder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommunityClickListener.onItemClick(position);
            }
        });

    }
    static class CommunityViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_touxiang;
        private TextView tv_name,tv_type,tv_content;
        private GridView gv_photo;
        private ImageView iv_video,iv_assess,iv_zan;
        private RelativeLayout rv_video;
        private TextView tv_date,tv_assess_num,tv_zan_num;
        private LinearLayout ll_assess,ll_zan,ll_item;
        public CommunityViewHolder(View itemView) {
            super(itemView);
            iv_touxiang = itemView.findViewById(R.id.iv_touxiang);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_content = itemView.findViewById(R.id.tv_content);
            gv_photo = itemView.findViewById(R.id.gv_photo);
            iv_video = itemView.findViewById(R.id.iv_video);
            iv_assess = itemView.findViewById(R.id.iv_assess);
            iv_zan = itemView.findViewById(R.id.iv_zan);
            rv_video = itemView.findViewById(R.id.rv_video);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_assess_num = itemView.findViewById(R.id.tv_assess_num);
            tv_zan_num = itemView.findViewById(R.id.tv_zan_num);
            ll_assess = itemView.findViewById(R.id.ll_assess);
            ll_zan = itemView.findViewById(R.id.ll_zan);
            ll_item = itemView.findViewById(R.id.ll_item);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public interface OnCommunityClickListener {
        void onVideoClick(int position);
        void onGridViewClick(int position);
        void onAssessClick(int position);
        void onZanClick(int position);
        void onItemClick(int position);
    }

    /**
     * 各个点击事件的接口
     */
    public OnCommunityClickListener onCommunityClickListener;

    public OnCommunityClickListener getOnCommunityClickListener() {
        return onCommunityClickListener;
    }

    public void setOnRecyclerViewItemClickListener(OnCommunityClickListener onCommunityClickListener) {
        this.onCommunityClickListener = onCommunityClickListener;
    }
}
