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

public class MallCarAdapter extends BaseAdapterRecycler<String> {
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;

    public MallCarAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
        layoutManager = recyclerView.getLayoutManager();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AgencyRecyclerAdapter.IndexViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mall_car,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MallViewHolder viewHolder = (MallViewHolder) holder;
        viewHolder.numView.setOnNumViewClickListener(new NumView.OnNumViewClickListener() {
            @Override
            public void onNumChangeClick(int num) {
                if(viewHolder.cb_select.isChecked()){//当前为选中状态
                    onMallItemClickListener.onNumChangeClick(viewHolder.numView.getNum());
                }
            }
        });

    }
    static class MallViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cb_select;
        private ImageView iv_mall;
        private TextView tv_mall_name,tv_mall_sign,tv_mall_price;
        private NumView numView;
        public MallViewHolder(@NonNull View itemView) {
            super(itemView);
            cb_select = itemView.findViewById(R.id.cb_select);
            iv_mall = itemView.findViewById(R.id.iv_mall);
            tv_mall_name = itemView.findViewById(R.id.tv_mall_name);
            tv_mall_sign = itemView.findViewById(R.id.tv_mall_sign);
            tv_mall_price = itemView.findViewById(R.id.tv_mall_price);
            numView = itemView.findViewById(R.id.numView);
        }
    }

    private OnMallItemClickListener onMallItemClickListener;

    public void setOnMallItemClickListener(OnMallItemClickListener onMallItemClickListener) {
        this.onMallItemClickListener = onMallItemClickListener;
    }

    public interface OnMallItemClickListener extends OnRecyclerViewItemClickListener{
        void onNumChangeClick(int num);
    }
}
