package com.thinker.vdongthinker.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.thinker.vdongthinker.tool.LogUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AdapterBase<T> extends BaseAdapter
{
    private String TAG = getClass().getSimpleName();
	protected Context context;
	protected LayoutInflater inflater;
	protected List<T> itemList = new ArrayList<T>();

	public AdapterBase(Context context)
	{
		super();
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	public boolean isEmpty()
	{
		return itemList.isEmpty();
	}

	public void addItems(List<T> itemList)
	{
		this.itemList.addAll(itemList);
		notifyDataSetChanged();
		LogUtils.i("PreferInvestAdapter", "addItems");
	}

	public void setItems(List<T> itemList)
	{
//		this.itemList.clear();
		this.itemList = itemList;
		notifyDataSetChanged();
		LogUtils.i("PreferInvestAdapter", "setItems");
	}

	public void clearItems()
	{
		itemList.clear();
		notifyDataSetChanged();
		LogUtils.i("PreferInvestAdapter", "clearItems");
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		// Log.i(TAG, itemList.size() + "");
		return itemList.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	abstract public View getView(int position, View convertView, ViewGroup parent);

}