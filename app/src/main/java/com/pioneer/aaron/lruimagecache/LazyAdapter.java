package com.pioneer.aaron.lruimagecache;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.pioneer.aaron.lruimagecache.AsyncImageLoader.AsyncImageLoader;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Aaron on 6/8/15.
 */
public class LazyAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private LinkedList<HashMap<String, Object>> items;
    private ImageLoader imageLoader;
    private AsyncImageLoader mAsyncImageLoader;

    static final String KEY_URL = "url";

    public LazyAdapter(Context context, LinkedList<HashMap<String, Object>> items) {
        mLayoutInflater = LayoutInflater.from(context);
        this.items = items;
        imageLoader = new ImageLoader();
        mAsyncImageLoader = new AsyncImageLoader();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.items, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        imageLoader.displayImage(items.get(position).get(KEY_URL).toString(), holder.imageView);
//        mAsyncImageLoader.loadImage(items.get(position).get(KEY_URL).toString(), holder.imageView);

        return convertView;

    }

    private class ViewHolder {
        private ImageView imageView;
    }
}
