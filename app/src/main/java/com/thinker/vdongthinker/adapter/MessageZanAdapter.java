package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.bean.MessageZanBean;

/**
 * Created by zjw on 2018/12/28.
 */
public class MessageZanAdapter extends BaseAdapterRecycler<MessageZanBean> {
    public MessageZanAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZanViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_zan,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ZanViewHolder viewHolder = (ZanViewHolder) holder;
        MessageZanBean bean = (MessageZanBean) getItem(position);
        if(bean.getIS_VEDIO() == 0){
            viewHolder.tv_num.setVisibility(View.VISIBLE);
            viewHolder.rl_vedio.setVisibility(View.GONE);
        }else{
            viewHolder.tv_num.setVisibility(View.GONE);
            viewHolder.rl_vedio.setVisibility(View.VISIBLE);
        }
        viewHolder.iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMessageZanClickListener.onCloseClick(position);
            }
        });


    }
    class ZanViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_touxiang,iv_close;
        private TextView tv_name,tv_time,tv_content;
        private ImageView iv_img;
        private TextView tv_num,tv_detail;
        private RelativeLayout rl_vedio;
        public ZanViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_touxiang = itemView.findViewById(R.id.iv_touxiang);
            iv_close = itemView.findViewById(R.id.iv_close);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_detail = itemView.findViewById(R.id.tv_detail);
            rl_vedio = itemView.findViewById(R.id.rl_vedio);

        }
    }

    public OnMessageZanClickListener onMessageZanClickListener;

    public void setOnMessageZanClickListener(OnMessageZanClickListener onMessageZanClickListener) {
        this.onMessageZanClickListener = onMessageZanClickListener;
    }

    public interface OnMessageZanClickListener extends OnRecyclerViewItemClickListener{
        void onCloseClick(int position);
    }
}
