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
import com.thinker.vdongthinker.bean.MessageReplayBean;
import com.thinker.vdongthinker.bean.MessageZanBean;

/**
 * Created by zjw on 2018/12/28.
 */
public class MessageReplayAdapter extends BaseAdapterRecycler<MessageReplayBean> {
    public MessageReplayAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReplayViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_replay,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ReplayViewHolder viewHolder = (ReplayViewHolder) holder;
        MessageReplayBean bean = (MessageReplayBean) getItem(position);
        viewHolder.iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMessageReplayClickListener.onCloseClick(position);
            }
        });


    }
    class ReplayViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_touxiang,iv_close;
        private TextView tv_name,tv_time,tv_content;
        private ImageView iv_img;
        private TextView tv_detail,tv_sign;
        public ReplayViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_touxiang = itemView.findViewById(R.id.iv_touxiang);
            iv_close = itemView.findViewById(R.id.iv_close);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_detail = itemView.findViewById(R.id.tv_detail);
            tv_sign = itemView.findViewById(R.id.tv_sign);

        }
    }

    public OnMessageReplayClickListener onMessageReplayClickListener;

    public void setOnMessageReplayClickListener(OnMessageReplayClickListener onMessageReplayClickListener) {
        this.onMessageReplayClickListener = onMessageReplayClickListener;
    }

    public interface OnMessageReplayClickListener extends OnRecyclerViewItemClickListener{
        void onCloseClick(int position);
    }
}
