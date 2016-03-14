package com.example.admin.medicare.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.admin.medicare.R;
import com.example.admin.medicare.activities.DetailsActivity;
import com.example.admin.medicare.activities.GenericActivity;
import com.example.admin.medicare.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10-03-2016.
 */
public class ListViewAdapter extends BaseAdapter implements Filterable{
    private Context mContext;
    private List<String> items;
    private SearchFilter searchFilter;
    private int fragmentNumber;

    public ListViewAdapter(Context context, List<String> items,int fragmentNumber) {
        this.items = items;
        this.fragmentNumber = fragmentNumber;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_view_single_item,parent,false);
        TextView tvItem = (TextView) convertView.findViewById(R.id.tvItem);
        tvItem.setText(items.get(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click listener for each list item
                switch (fragmentNumber){
                    case Constants.FRAGMENT_CATEGORY:
                        goToNextActivity(GenericActivity.class,items.get(position));
                        break;
                    case Constants.FRAGMENT_BRAND:
                        for (int i = 0; i < items.size(); i++) {
                            items.set(i, items.get(i).replaceAll("•", "�"));
                        }
                        Intent intent = new Intent();
                        intent.setClass(mContext.getApplicationContext(), DetailsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(Constants.BRAND_LIST_KEY,items.get(position));
                        mContext.startActivity(intent);
                        break;
                    case Constants.FRAGMENT_GENERIC:
                        Intent intent1 = new Intent();
                        intent1.setClass(mContext.getApplicationContext(), DetailsActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent1.putExtra(Constants.GENERIC_LIST_KEY,items.get(position));
                        mContext.startActivity(intent1);
                        break;
                }
            }
        });
        return convertView;
    }

    protected void goToNextActivity(Class nextActivity,String item) {
        Intent intent = new Intent();
        intent.setClass(mContext.getApplicationContext(), nextActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.CATEGORY_LIST_KEY,item);
        intent.putExtra(Constants.GENERIC_LIST_KEY, item);
        intent.putExtra(Constants.BRAND_LIST_KEY,item);
        mContext.startActivity(intent);
    }

    @Override
    public Filter getFilter() {
        if (searchFilter == null)
            searchFilter = new SearchFilter();

        return searchFilter;
    }

    private class SearchFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
// No filter implemented we return all the list
                results.values = items;
                results.count = items.size();
            }
            else {
// We perform filtering operation
                List<String> item = new ArrayList<String>();

               for (int i=0;i<items.size();i++){
                   char c=items.get(i).charAt(0);
                   char b =constraint.charAt(0);
                   if (c==b ){
                       item.add(items.get(i));
                       Log.d("searcg", item.toString());
                   }
               }
                results.values = item;
                results.count = item.size();

            }
            Log.d("search", results.toString());
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                items = (List<String>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
