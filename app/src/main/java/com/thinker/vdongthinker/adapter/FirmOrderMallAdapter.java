package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.customControl.NumView;

public class FirmOrderMallAdapter extends BaseAdapterRecycler<String> {
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;

    public FirmOrderMallAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
        layoutManager = recyclerView.getLayoutManager();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AgencyRecyclerAdapter.IndexViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_mall,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MallViewHolder viewHolder = (MallViewHolder) holder;


    }
    static class MallViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_mall;
        private TextView tv_mall_name,tv_mall_sign,tv_mall_price;
        private NumView numView;
        public MallViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_mall = itemView.findViewById(R.id.iv_mall);
            tv_mall_name = itemView.findViewById(R.id.tv_mall_name);
            tv_mall_sign = itemView.findViewById(R.id.tv_mall_sign);
            tv_mall_price = itemView.findViewById(R.id.tv_mall_price);
            numView = itemView.findViewById(R.id.numView);
        }
    }


}
