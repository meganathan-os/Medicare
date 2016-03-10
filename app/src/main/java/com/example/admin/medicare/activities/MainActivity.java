package com.example.admin.medicare.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.admin.medicare.R;
import com.example.admin.medicare.fragments.MedicareFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialiseTabLayout();
    }

    /**
     * Method to initialise the Tab Layout and set Text
     */
    private void initialiseTabLayout() {
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(getResources()
                .getText(R.string.tab_category)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources()
                .getText(R.string.tab_generic)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources()
                .getText(R.string.tab_brand)));
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
            case 0:
                changeFragment(MedicareFragment.class.getName(),0);
                break;
            case 1:
                changeFragment(MedicareFragment.class.getName(),1);
                break;
            case 2:
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
}