package com.example.admin.medicare.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.medicare.R;
import com.example.admin.medicare.adapters.GenericListViewAdapter;
import com.example.admin.medicare.utilities.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 11-03-2016.
 */
public class GenericActivity extends AppCompatActivity implements View.OnClickListener {
    private GenericListViewAdapter genericListViewAdapter;
    private DatabaseHelper databaseHelper = null;
    ListView listView;
    Map<String, Integer> mapIndex;
    String[] glist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);
        initialiseDatabaseHelperClass();
        getGenericListFromDB();
    }

    private void getGenericListFromDB(){
        Bundle bundle = getIntent().getExtras();
        String genericListString=bundle.getString("generic_list_key");
        List<String> genericList = databaseHelper.getAllDrugs(genericListString);
        Collections.sort(genericList);
        setAdapterToListView(genericList);




    }

    private void initialiseDatabaseHelperClass(){
        try {
            databaseHelper=new DatabaseHelper(this,this.getFilesDir().getAbsolutePath());
            databaseHelper.prepareDatabase();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            Log.e(getClass().getName(), e1.getMessage());
        }
    }

    private void setAdapterToListView(List<String> items) {
        listView = (ListView) findViewById(R.id.llGenericList);
        genericListViewAdapter = new GenericListViewAdapter(this, items);
        listView.setAdapter(genericListViewAdapter);

        glist=new String[items.size()];
        glist=items.toArray(glist);
        Arrays.asList(glist);
        getIndexList(glist);
        displayIndex();


    }
    private void getIndexList(String[] categs) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < categs.length; i++) {
            String categ = categs[i];
            String index = categ.substring(0, 1);
          /*  for(int j=0;j<=9;j++)
            {
                if(Integer.parseInt(index)==j)
                {
                    index="#";
                }
            }*/

            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }

    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);

        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(R.layout.list_view_single_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
    }


    @Override
    public void onClick(View v) {
        TextView selectedIndex = (TextView) v;
        listView.setSelection(mapIndex.get(selectedIndex.getText()));
    }
}
