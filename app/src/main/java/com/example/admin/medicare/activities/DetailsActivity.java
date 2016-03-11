package com.example.admin.medicare.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.admin.medicare.R;

/**
 * Created by Admin on 11-03-2016.
 */
public class DetailsActivity extends AppCompatActivity {
    private TextView tvCategory,tvDrugClass,tvPharma,tvDailyDose,tvPregnancyEffects,
            tvLactatingEffects,tvSedation,tvWeightGain,tvSideEffects,tvLifeThreatening,
            tvDrugInteraction,tvTest,tvOverdose,tvExtraInformation,tvReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
    }

    private void initViews(){
        tvCategory=(TextView) findViewById(R.id.tvCategory);
        tvDrugClass=(TextView) findViewById(R.id.tvDrugClass);
        tvPharma=(TextView) findViewById(R.id.tvPharma);
        tvDailyDose=(TextView) findViewById(R.id.tvDailyDose);
        tvPregnancyEffects=(TextView) findViewById(R.id.tvPregnancyEffects);
        tvLactatingEffects=(TextView) findViewById(R.id.tvLactatingEffects);
        tvSedation=(TextView) findViewById(R.id.tvSedation);
        tvWeightGain=(TextView) findViewById(R.id.tvWeightGain);
        tvSideEffects=(TextView) findViewById(R.id.tvSideEffects);
        tvLifeThreatening=(TextView) findViewById(R.id.tvLifeThreatening);
        tvDrugInteraction=(TextView) findViewById(R.id.tvDrugInteraction);
        tvTest=(TextView) findViewById(R.id.tvTest);
        tvOverdose=(TextView) findViewById(R.id.tvOverdose);
        tvExtraInformation=(TextView) findViewById(R.id.tvExtraInformation);
        tvReference=(TextView) findViewById(R.id.tvReference);
    }
}
