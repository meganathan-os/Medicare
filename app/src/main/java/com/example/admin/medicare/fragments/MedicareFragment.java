package com.example.admin.medicare.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.admin.medicare.R;
import com.example.admin.medicare.activities.MainActivity;
import com.example.admin.medicare.adapters.ListViewAdapter;

import java.util.List;

/**
 * Created by Admin on 10-03-2016.
 */
public class MedicareFragment extends Fragment {
    private static final String FRAGMENT_NUMBER = "param1";
    private int mFragmentNumber;
    private View rootView;
    private Context mContext;

    public MedicareFragment() {
    }

    public static MedicareFragment newInstance(int fragmentNumber) {
        MedicareFragment fragment = new MedicareFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_NUMBER, fragmentNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
        if (getArguments() != null) {
            mFragmentNumber = getArguments().getInt(FRAGMENT_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_medicare, container, false);
        setAdapterToFragment();
        return rootView;
    }


    private void setAdapterToFragment() {
        switch (mFragmentNumber) {
            case 0:
                List<String> items = ((MainActivity) getActivity()).getCategoryItems();
                Log.d("category", items.toString());
                setAdapterToListView(items);
                break;
            case 1:
                items = ((MainActivity) getActivity()).getBrandItems();
                Log.d("brand", items.toString());
                for (int i = 0; i < items.size(); i++) {
                    items.set(i, items.get(i).replaceAll("�", ","));
                }
                setAdapterToListView(items);
                break;
            case 2:
                items = ((MainActivity) getActivity()).getGenericItems();
                Log.d("generic", items.toString());
                setAdapterToListView(items);
                break;
        }
    }

    private void setAdapterToListView(List<String> items) {
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        ListViewAdapter listViewAdapter = new ListViewAdapter(mContext, items);
        listView.setAdapter(listViewAdapter);
    }
}
