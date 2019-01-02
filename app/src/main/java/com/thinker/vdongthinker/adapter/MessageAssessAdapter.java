package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.bean.MessageZanBean;

/**
 * Created by zjw on 2018/12/28.
 */
public class MessageAssessAdapter extends BaseAdapterRecycler<MessageZanBean> {
    public MessageAssessAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AssessViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_zan,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AssessViewHolder viewHolder = (AssessViewHolder) holder;
        MessageZanBean bean = (MessageZanBean) getItem(position);

        viewHolder.iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMessageZanClickListener.onCloseClick(position);
            }
        });


    }
    class AssessViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_touxiang,iv_close;
        private TextView tv_name,tv_time,tv_content;
        private ImageView iv_img;
        private TextView tv_detail;
        private LinearLayout ll_replay;
        public AssessViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_touxiang = itemView.findViewById(R.id.iv_touxiang);
            iv_close = itemView.findViewById(R.id.iv_close);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_detail = itemView.findViewById(R.id.tv_detail);
            ll_replay = itemView.findViewById(R.id.ll_replay);
            ll_replay.setVisibility(View.VISIBLE);
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
