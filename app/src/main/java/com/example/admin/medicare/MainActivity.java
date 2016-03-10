package com.example.admin.medicare;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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
    }
}
