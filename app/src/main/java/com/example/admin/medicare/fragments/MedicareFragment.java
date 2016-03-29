package com.example.admin.medicare.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.admin.medicare.R;
import com.example.admin.medicare.activities.MainActivity;
import com.example.admin.medicare.adapters.ListViewAdapter;
import com.example.admin.medicare.utilities.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 10-03-2016.
 */
public class MedicareFragment extends Fragment implements View.OnClickListener {
    private static final String FRAGMENT_NUMBER = "param1";
    private int mFragmentNumber;
    private View rootView;
    private Context mContext;
    private List<String> items;
    private Map<String, Integer> mapIndex;
    private ListView listView;
    private SearchView searchBar;
    private ListViewAdapter listViewAdapter;
    String[] glist;

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
        listView = (ListView) rootView.findViewById(R.id.listView);
        searchBar = (SearchView) rootView.findViewById(R.id.etSearchBar);
        setAdapterToFragment(inflater,rootView);
        return rootView;
    }


    private void setAdapterToFragment(LayoutInflater inflater,View v) {
        switch (mFragmentNumber) {
            case Constants.FRAGMENT_CATEGORY:
                items = ((MainActivity) getActivity()).getCategoryItems();
                Collections.sort(items);
                convertListToStringArray(items);
                setAdapterToListView(items);
                search();

                glist=new String[items.size()];
                glist=items.toArray(glist);
                Arrays.asList(glist);
                getIndexList(glist);
                displayIndex(inflater,v);
                break;
            case Constants.FRAGMENT_BRAND:
                items = ((MainActivity) getActivity()).getBrandItems();
                Collections.sort(items);
                for (int i = 0; i < items.size(); i++) {
                    items.set(i, items.get(i).replaceAll("�", "•"));
                }
                convertListToStringArray(items);
                setAdapterToListView(items);
                search();

                glist=new String[items.size()];
                glist=items.toArray(glist);
                Arrays.asList(glist);
                getIndexList(glist);
                displayIndex(inflater,v);
                break;
            case Constants.FRAGMENT_GENERIC:
                items = ((MainActivity) getActivity()).getGenericItems();
                Collections.sort(items);
                convertListToStringArray(items);
                setAdapterToListView(items);
                search();

                glist=new String[items.size()];
                glist=items.toArray(glist);
                Arrays.asList(glist);
                getIndexList(glist);
                displayIndex(inflater,v);
                break;
        }
    }

    private void convertListToStringArray(List<String> list) {
        String[] clist = new String[list.size()];
        clist = list.toArray(clist);
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        getIndexList(clist);
    }

    private void setAdapterToListView(List<String> items) {
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter(mContext, items, mFragmentNumber);
        listView.setAdapter(listViewAdapter);
    }


    private void getIndexList(String[] categs) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < categs.length; i++) {
            String categ = categs[i];
            //String index = categ.substring(0, 1);
            String index = categ.toUpperCase().substring(0, 1);

            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }

    private void displayIndex(LayoutInflater inflater,View v) {
        LinearLayout indexLayout = (LinearLayout) v.findViewById(R.id.side_index);

        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) inflater.inflate(R.layout.list_view_single_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
    }

    private void search() {
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        TextView selectedIndex = (TextView) v;
        listView.setSelection(mapIndex.get(selectedIndex.getText()));
    }
}
