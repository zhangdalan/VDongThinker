package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.bean.IndexMallRecyclerBean;
import com.thinker.vdongthinker.tool.GlideRoundTransform;

import java.util.List;

/**
 * Created by zjw on 2018/12/10.
 */

public class IndexRecyclerAdapter extends BaseAdapterRecycler<IndexMallRecyclerBean> {

    private Context mContext;
//    private List<IndexMallRecyclerBean> listMall;
    private RecyclerView.LayoutManager layoutManager;

    public IndexRecyclerAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
        this.mContext = context;
        recyclerView.addItemDecoration(new IndexRecyclerItemDecoration(50,0));
        layoutManager = recyclerView.getLayoutManager();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IndexViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_mall,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        IndexMallRecyclerBean bean = (IndexMallRecyclerBean) getItem(position);
        final IndexViewHolder viewHolder = (IndexViewHolder) holder;
        viewHolder.tv_mall_title.setText(bean.getTitle());
        viewHolder.tv_price.setText("¥"+bean.getPrice());
        viewHolder.tv_num.setText(bean.getNum()+"人已购买");
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.mipmap.img_defult_mall).priority(Priority.HIGH).transform(new GlideRoundTransform(10, GlideRoundTransform.CornerType.TOP));
        Glide.with(mContext).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545198539&di=1467c52940267a6d1cfc5cfa318cb978&imgtype=jpg&er=1&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Farchive%2Fa73f4d508193aeac508f39dce297a9af4a69f65e.jpg").apply(options).into(viewHolder.iv_mall);
        RequestOptions options1 = new RequestOptions().centerCrop().transform(new GlideRoundTransform(10, GlideRoundTransform.CornerType.TOP_LEFT));
        Glide.with(mContext).load(R.mipmap.img_hot).apply(options1).into(viewHolder.iv_hot);
        viewHolder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onItemClick(v,position);
            }
        });
    }

    static class IndexViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_hot,iv_mall,iv_detail;
        private TextView tv_mall_title,tv_price,tv_num;
        private LinearLayout ll_item;
        public IndexViewHolder(View itemView) {
            super(itemView);
            iv_hot = itemView.findViewById(R.id.iv_hot);
            iv_mall = itemView.findViewById(R.id.iv_mall);
            iv_detail = itemView.findViewById(R.id.iv_detail);
            tv_mall_title = itemView.findViewById(R.id.tv_mall_title);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_num = itemView.findViewById(R.id.tv_num);
            ll_item = itemView.findViewById(R.id.ll_item);
        }
    }

}
