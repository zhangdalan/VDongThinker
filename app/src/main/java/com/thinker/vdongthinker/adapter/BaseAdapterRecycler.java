package com.thinker.vdongthinker.adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/1/26.
 */
public abstract class BaseAdapterRecycler<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> itemList = new ArrayList<T>();
    public final LayoutInflater mLayoutInflater;
    private Context mContext;
    public static final int EMPTY_VIEW = 1;
    public static final int VIEW_PROG = 2;
    public static final int VIEW_END = 3;

    private int visibleThreshold = 1;
    private int lastVisibleItem, totalItemCount;
    private boolean loading = false;
    private boolean isEnd = false;
    private OnLoadMoreListener onLoadMoreListener;

    public BaseAdapterRecycler(Context context, RecyclerView recyclerView) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold) && !isEnd) {
                        // End has been reached
                        // Do something
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                    }
                }
            });
        }
    }

    @Override
    abstract public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);


    @Override
    abstract public void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return itemList.size() > 0 ? itemList.size() : 1;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    public void addItem(T obj) {
        this.itemList.add(obj);
        notifyDataSetChanged();
    }

    public void addItemPosition(int position, T obj) {
        this.itemList.add(position, obj);
        notifyDataSetChanged();
    }

    public void delLastItem() {
        this.itemList.remove(itemList.size() - 1);
        notifyDataSetChanged();
    }

    public void addItems(List<T> itemList) {
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    public void setItems(List<T> itemList) {
//        this.itemList.clear();
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public void clearItems() {
        itemList.clear();
        notifyDataSetChanged();
    }

    public Object getItem(int position) {
        return itemList.get(position);
    }


    /**
     * 加载中
     */
    public void setLoading() {
        loading = true;
    }

    /**
     * 加载结束
     */
    public void setLoaded() {
        loading = false;
    }

    public void setEnd() {
        isEnd = true;
    }

    public void setNoEnd() {
        isEnd = false;
    }

    @Override
    public int getItemViewType(int position) {
        if (itemList.size() == 0) {
            return EMPTY_VIEW;
        }
        if (itemList.get(position) != null) {
            return super.getItemViewType(position);
        } else {
            if (isEnd) {
                return VIEW_END;
            } else {
                return VIEW_PROG;
            }
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 条目点击事件的接口
     */
    public OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public OnRecyclerViewItemClickListener getOnRecyclerViewItemClickListener() {
        return onRecyclerViewItemClickListener;
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
