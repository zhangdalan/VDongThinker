package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.customControl.NumView;

public class SelectAddressAdapter extends BaseAdapterRecycler<String> {
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    private boolean IS_STUDENT = false;

    public SelectAddressAdapter(Context context, RecyclerView recyclerView,boolean IS_STUDENT) {
        super(context, recyclerView);
        layoutManager = recyclerView.getLayoutManager();
        this.IS_STUDENT = IS_STUDENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AgencyRecyclerAdapter.IndexViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MallViewHolder viewHolder = (MallViewHolder) holder;
        if(IS_STUDENT){
            viewHolder.tv_name.setText("学生：");
            viewHolder.tv_tel.setVisibility(View.GONE);
        }else{
            viewHolder.tv_name.setText("收货人：");
            viewHolder.tv_tel.setVisibility(View.VISIBLE);
        }

    }
    static class MallViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_tel,tv_address,tv_edit;
        public MallViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_tel = itemView.findViewById(R.id.tv_tel);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_edit = itemView.findViewById(R.id.tv_edit);
        }
    }


}
