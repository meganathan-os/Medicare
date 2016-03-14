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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 10-03-2016.
 */
public class MedicareFragment extends Fragment {
    private static final String FRAGMENT_NUMBER = "param1";
    private int mFragmentNumber;
    private View rootView;
    private Context mContext;
    private List<String> items;
    private Map<String, Integer> mapIndex;
    private ListView listView;
    private EditText searchBar;
    private ListViewAdapter listViewAdapter;

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
        searchBar = (EditText) rootView.findViewById(R.id.etSearchBar);
        setAdapterToFragment();
        displayIndex(inflater);
        return rootView;
    }


    private void setAdapterToFragment() {
        switch (mFragmentNumber) {
            case Constants.FRAGMENT_CATEGORY:
                items = ((MainActivity) getActivity()).getCategoryItems();
                Collections.sort(items);
                convertListToStringArray(items);
                setAdapterToListView(items);
                search();
                break;
            case Constants.FRAGMENT_BRAND:
                items = ((MainActivity) getActivity()).getBrandItems();
                Collections.sort(items);
                for (int i = 0; i < items.size(); i++) {
                    items.set(i, items.get(i).replaceAll("�", "•"));
                }
                convertListToStringArray(items);
                setAdapterToListView(items);
                break;
            case Constants.FRAGMENT_GENERIC:
                items = ((MainActivity) getActivity()).getGenericItems();
                Collections.sort(items);
                convertListToStringArray(items);
                setAdapterToListView(items);
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

    private void displayIndex(LayoutInflater inflater) {
        LinearLayout llSideIndexLayout = (LinearLayout) rootView.findViewById(R.id.llIndexSideLayout);
        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) inflater.inflate(R.layout.index_side_item, null);

            textView.setText(index);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView selectedIndex = (TextView) v;
                    listView.setSelection(mapIndex.get(selectedIndex.getText()));
                }
            });
            llSideIndexLayout.addView(textView);
        }
    }

    private void search() {
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listViewAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                listViewAdapter.getFilter();
            }
        });
    }
}
