package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.bean.MyCourseBean;
import com.thinker.vdongthinker.customControl.NumView;

public class MyCourseAdapter extends BaseAdapterRecycler<MyCourseBean> {
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;

    public MyCourseAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
        layoutManager = recyclerView.getLayoutManager();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AgencyRecyclerAdapter.IndexViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mall_car,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MallViewHolder viewHolder = (MallViewHolder) holder;
        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMallItemClickListener.onDeleteListener(position);
            }
        });
        viewHolder.btn_assess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMallItemClickListener.onAssessListener(position);
            }
        });

    }
    static class MallViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_img;
        private TextView tv_agency_name,tv_sign,tv_price,tv_name;
        private Button btn_delete,btn_assess;
        public MallViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_agency_name = itemView.findViewById(R.id.tv_agency_name);
            tv_sign = itemView.findViewById(R.id.tv_sign);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_name = itemView.findViewById(R.id.tv_name);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_assess = itemView.findViewById(R.id.btn_assess);
        }
    }

    private OnMallItemClickListener onMallItemClickListener;

    public void setOnMallItemClickListener(OnMallItemClickListener onMallItemClickListener) {
        this.onMallItemClickListener = onMallItemClickListener;
    }

    public interface OnMallItemClickListener extends OnRecyclerViewItemClickListener{
        void onDeleteListener(int position);
        void onAssessListener(int position);
    }
}
