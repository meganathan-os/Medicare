package com.example.admin.medicare.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.admin.medicare.R;
import com.example.admin.medicare.fragments.MedicareFragment;
import com.example.admin.medicare.utilities.Constants;
import com.example.admin.medicare.utilities.DatabaseHelper;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper = null;
    private List<String> categoryItems, genericItems,brandItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseTabLayout();
        initialiseDatabaseHelperClass();
        categoryItems = databaseHelper.getEntireCateg();
        brandItems = databaseHelper.getEntireBrand();
        genericItems = databaseHelper.getEntireGeneric();
        changeFragment(MedicareFragment.class.getName(),0);
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

    /**
     * Method to initialise the Tab Layout and set Text
     */
    private void initialiseTabLayout() {
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(getResources()
                .getText(R.string.tab_category)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources()
                .getText(R.string.tab_brand)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources()
                .getText(R.string.tab_generic)));
        tabLayout.setTabTextColors(getResources().getColor(R.color.color_grey), getResources()
                .getColor(R.color.colorPrimary));
        setTabClickListener(tabLayout);
    }

    /**
     * Method to set click listeners to the Tab Layout
     *
     * @param tabLayout
     */
    private void setTabClickListener(final TabLayout tabLayout) {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabClickListener(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void tabClickListener(TabLayout.Tab tab){
        int id = tab.getPosition();
        switch (id){
            case Constants.FRAGMENT_CATEGORY:
                changeFragment(MedicareFragment.class.getName(),0);
                break;
            case Constants.FRAGMENT_BRAND:
                changeFragment(MedicareFragment.class.getName(),1);
                break;
            case Constants.FRAGMENT_GENERIC:
                changeFragment(MedicareFragment.class.getName(),2);
                break;
        }
    }

    private void changeFragment(String className,int param){
        try {
            Fragment fragment = MedicareFragment.newInstance(param);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.flContainer, fragment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getCategoryItems(){
        return categoryItems;
    }

    public List<String> getGenericItems(){
        return genericItems;
    }

    public List<String> getBrandItems(){
        return brandItems;
    }
}