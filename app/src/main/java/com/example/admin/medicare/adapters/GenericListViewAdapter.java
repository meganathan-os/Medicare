package com.example.admin.medicare.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.medicare.R;
import com.example.admin.medicare.activities.DetailsActivity;
import com.example.admin.medicare.utilities.Constants;

import java.util.List;

/**
 * Created by Admin on 11-03-2016.
 */
public class GenericListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> items;

    public GenericListViewAdapter(Context context, List<String> items) {
        this.mContext = context;
        this.items = items;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_view_single_item,parent,false);
        TextView tvItem = (TextView) convertView.findViewById(R.id.tvItem);
        tvItem.setText(items.get(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity(DetailsActivity.class,items.get(position));
            }
        });
        return convertView;
    }

    protected void goToNextActivity(Class nextActivity,String item) {
        Intent intent = new Intent();
        intent.setClass(mContext.getApplicationContext(), nextActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.GENERIC_LIST_KEY, item);
        mContext.startActivity(intent);
    }
}
