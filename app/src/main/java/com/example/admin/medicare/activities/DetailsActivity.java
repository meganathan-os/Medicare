package com.example.admin.medicare.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.admin.medicare.R;
import com.example.admin.medicare.utilities.Constants;
import com.example.admin.medicare.utilities.DatabaseHelper;

import java.io.IOException;

/**
 * Created by Admin on 11-03-2016.
 */
public class DetailsActivity extends AppCompatActivity {
    private TextView tvGenericName, tvCategory, tvDrugClass, tvPharma, tvDailyDose, tvPregnancyEffects,
            tvLactatingEffects, tvSedation, tvWeightGain, tvSideEffects, tvLifeThreatening,
            tvDrugInteraction, tvTest, tvOverdose, tvExtraInformation, tvReference, tvBrandName;
    private DatabaseHelper databaseHelper = null;
    private Cursor resultset;
    String brandListKey, genericListKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initialiseDatabaseHelperClass();
        initViews();
        getDetailsListFromDB();
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


    private void initViews() {
        tvGenericName = (TextView) findViewById(R.id.tvGenericName);
        tvCategory = (TextView) findViewById(R.id.tvCategory);
        tvDrugClass = (TextView) findViewById(R.id.tvDrugClass);
        tvPharma = (TextView) findViewById(R.id.tvPharma);
        tvDailyDose = (TextView) findViewById(R.id.tvDailyDose);
        tvPregnancyEffects = (TextView) findViewById(R.id.tvPregnancyEffects);
        tvLactatingEffects = (TextView) findViewById(R.id.tvLactatingEffects);
        tvSedation = (TextView) findViewById(R.id.tvSedation);
        tvWeightGain = (TextView) findViewById(R.id.tvWeightGain);
        tvSideEffects = (TextView) findViewById(R.id.tvSideEffects);
        tvLifeThreatening = (TextView) findViewById(R.id.tvLifeThreatening);
        tvDrugInteraction = (TextView) findViewById(R.id.tvDrugInteraction);
        tvTest = (TextView) findViewById(R.id.tvTest);
        tvOverdose = (TextView) findViewById(R.id.tvOverdose);
        tvExtraInformation = (TextView) findViewById(R.id.tvExtraInformation);
        tvReference = (TextView) findViewById(R.id.tvReference);
        tvBrandName = (TextView) findViewById(R.id.tvBrandName);
    }

    private void getDetailsListFromDB() {
        Bundle bundle = getIntent().getExtras();
        brandListKey = bundle.getString(Constants.BRAND_LIST_KEY);
        genericListKey = bundle.getString(Constants.GENERIC_LIST_KEY);
        if (brandListKey == null) {
            resultset = databaseHelper.getEntireData(genericListKey);
        }else {
            resultset = databaseHelper.getEntireData1(brandListKey);
        }
        resultset.moveToFirst();
        if (resultset!=null && resultset.moveToFirst()){
            getDataFromTheListAndSetTextToView(resultset);
        }
    }

    private void getDataFromTheListAndSetTextToView(Cursor resultset) {
        String genericName = (resultset.getString(resultset.
                getColumnIndex(DatabaseHelper.GENERIC_NAME)).trim().
                replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String brandName = (resultset.getString(resultset.
                getColumnIndex(DatabaseHelper.BRAND_NAME)).trim().
                replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String category = (resultset.getString(resultset.getColumnIndex(DatabaseHelper.CATEGORY)).
                trim().replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String moa = (resultset.getString(resultset.getColumnIndex(DatabaseHelper.MOA)).
                trim().replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String pharma = (resultset.getString(resultset.
                getColumnIndex(DatabaseHelper.PHARMACOKINETICS)).trim().
                replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String dose = (resultset.getString(resultset.getColumnIndex(DatabaseHelper.DAILY_DOSAGE)).
                trim().replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String preg = (resultset.getString(resultset.
                getColumnIndex(DatabaseHelper.PREGNANCY_EFFECTS)).trim().
                replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String lact = (resultset.getString(resultset.
                getColumnIndex(DatabaseHelper.LACTATION_EFFECTS)).trim().
                replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String sedat = (resultset.getString(resultset.getColumnIndex(DatabaseHelper.SEDATION)).
                trim().replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String weight = (resultset.getString(resultset.getColumnIndex(DatabaseHelper.WEIGHT_GAIN)).
                trim().replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String side = (resultset.getString(resultset.getColumnIndex(DatabaseHelper.SIDE_EFFECTS)).
                trim().replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String life = (resultset.getString(resultset.
                getColumnIndex(DatabaseHelper.LIFE_THREATENING)).trim().
                replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String interact = (resultset.getString(resultset.
                getColumnIndex(DatabaseHelper.DRUG_INTERACTIONS)).trim().
                replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String tests = (resultset.getString(resultset.getColumnIndex(DatabaseHelper.TESTS)).trim().
                replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String overdose = (resultset.getString(resultset.getColumnIndex(DatabaseHelper.OVERDOSE)).
                trim().replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String info = (resultset.getString(resultset.
                getColumnIndex(DatabaseHelper.EXTRA_INFORMATION)).trim().
                replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        String ref = (resultset.getString(resultset.getColumnIndex(DatabaseHelper.REFERENCE)).
                trim().replaceAll("�", "\n\t • ").replaceAll("\\?", "\n\t ✽ "));

        if (!resultset.isClosed()) {
            resultset.close();
        }

        tvGenericName.setText("GENERIC : \n" + (CharSequence) genericName);
        tvBrandName.setText("BRAND : \n" + (CharSequence) brandName);
        tvCategory.setText("CATEGORY : \n" + (CharSequence) category);
        tvDrugClass.setText("MOA : \n" + (CharSequence) moa);
        tvPharma.setText("PHARMACOKINETICS : \n" + (CharSequence) pharma);
        tvDailyDose.setText("DAILY DOSE : \n" + (CharSequence) dose);
        tvPregnancyEffects.setText("PREGNANCY EFFECTS : \n" + (CharSequence) preg);
        tvLactatingEffects.setText("LACTATION EFFECTS : \n" + (CharSequence) lact);
        tvSedation.setText("SEDATION : \n" + (CharSequence) sedat);
        tvWeightGain.setText("WEIGHT GAIN : \n" + (CharSequence) weight);
        tvSideEffects.setText("SIDE EFFECTS : \n" + (CharSequence) side);
        tvLifeThreatening.setText("LIFE THREATENING SIDE EFFECTS : \n" + (CharSequence) life);
        tvDrugInteraction.setText("DRUG INTERACTIONS : \n" + (CharSequence) interact);
        tvTest.setText("TESTS : \n" + (CharSequence) tests);
        tvOverdose.setText("OVERDOSE : \n" + (CharSequence) overdose);
        tvExtraInformation.setText("EXTRA INFORMATION : \n" + (CharSequence) info);
        tvReference.setText("REFERENCE : \n" + (CharSequence) ref);

    }
}
