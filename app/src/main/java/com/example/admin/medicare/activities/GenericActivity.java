package com.example.admin.medicare.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import com.example.admin.medicare.R;
import com.example.admin.medicare.adapters.GenericListViewAdapter;
import com.example.admin.medicare.utilities.DatabaseHelper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 11-03-2016.
 */
public class GenericActivity extends AppCompatActivity {
    private GenericListViewAdapter genericListViewAdapter;
    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);
        initialiseDatabaseHelperClass();
        getGenericListFromDB();
    }

    private void getGenericListFromDB(){
        Bundle bundle = getIntent().getExtras();
        String genericListString=bundle.getString("string");
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
        ListView listView = (ListView) findViewById(R.id.llGenericList);
        genericListViewAdapter = new GenericListViewAdapter(this, items);
        listView.setAdapter(genericListViewAdapter);
    }

}
