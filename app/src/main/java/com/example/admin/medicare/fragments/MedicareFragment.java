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
import com.example.admin.medicare.adapters.ListViewAdapter;
import com.example.admin.medicare.utilities.DatabaseHelper;

import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 10-03-2016.
 */
public class MedicareFragment extends Fragment{
    private static final String FRAGMENT_NUMBER = "param1";
    private int mFragmentNumber;
    private View rootView;
    private ListView listView;
    private List<String> items;
    private Context mContext;
    private DatabaseHelper databaseHelper=null;
    public MedicareFragment(){

    }

    public static MedicareFragment newInstance(int fragmentNumber){
        MedicareFragment fragment = new MedicareFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_NUMBER, fragmentNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialiseDatabaseHelperClass();
        if (getArguments() != null) {
            mFragmentNumber = getArguments().getInt(FRAGMENT_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_medicare,container,false);
        setAdapterToFragment();
        return rootView;
    }

    private void initialiseDatabaseHelperClass(){
        try {
            databaseHelper=new DatabaseHelper(mContext,mContext.getFilesDir().getAbsolutePath());
            databaseHelper.prepareDatabase();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            Log.e(getClass().getName(), e1.getMessage());
        }
    }

    private void setAdapterToFragment(){
        switch (mFragmentNumber){
            case 0:
                List<String> categoryItems = databaseHelper.getEntireCateg();
                setAdapterToListView(categoryItems);
                break;
            case 1:
                List<String> brandItems = databaseHelper.getEntireBrand();
                for (int i=0;i<brandItems.size();i++){
                    brandItems.set(i, brandItems.get(i).replaceAll("�", ","));
                }
                setAdapterToListView(brandItems);
                break;
            case 2:
                List<String> genericItems = databaseHelper.getEntireGeneric();
                setAdapterToListView(genericItems);
                break;
        }
    }

    private void setAdapterToListView(List<String> items){
        listView = (ListView) rootView.findViewById(R.id.listView);
        ListViewAdapter listViewAdapter = new ListViewAdapter(mContext,items);
        listView.setAdapter(listViewAdapter);
    }
}
