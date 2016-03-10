package com.example.admin.medicare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.admin.medicare.R;

import java.util.List;

/**
 * Created by Admin on 10-03-2016.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> items;

    public ListViewAdapter(Context context, List<String> items) {
        this.items = items;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View singleRow = convertView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        singleRow = inflater.inflate(R.layout.list_view_single_item,parent,false);
        singleRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click listener for each list item
            }
        });
        return singleRow;
    }
}