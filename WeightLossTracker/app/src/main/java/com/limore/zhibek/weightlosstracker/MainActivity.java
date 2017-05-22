package com.limore.zhibek.weightlosstracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.limore.zhibek.weightlosstracker.Data.DBHelper;
import com.limore.zhibek.weightlosstracker.Data.DatabaseEntry;

public class MainActivity extends AppCompatActivity {

    TextView currentWeight,
            lostWeight,
            weightPlusMinus,
            chestPlusMinus,
            chestLoss,
            waistPlusMinus,
            waistLoss,
            bellyPlusMinus,
            bellyLoss,
            hipsPlusMinus,
            hipsLoss,
            thighPlusMinus,
            thighLoss,
            armPlusMinus,
            armLoss;

    FloatingActionButton addWeightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        currentWeight = (TextView) findViewById(R.id.currentWeight);
        lostWeight = (TextView) findViewById(R.id.lostWeight);
        weightPlusMinus = (TextView) findViewById(R.id.weightPlusMinus);

        chestPlusMinus =  (TextView) findViewById(R.id.chestPlusMinus);
        chestLoss = (TextView) findViewById(R.id.chestLoss);
        waistPlusMinus = (TextView) findViewById(R.id.waistPlusMinus);
        waistLoss =  (TextView) findViewById(R.id.waistLoss);
        bellyPlusMinus = (TextView) findViewById(R.id.bellyPlusMinus);
        bellyLoss = (TextView) findViewById(R.id.bellyLoss);
        hipsPlusMinus = (TextView) findViewById(R.id.hipsPlusMinus);
        hipsLoss = (TextView) findViewById(R.id.hipsLoss);
        thighPlusMinus = (TextView) findViewById(R.id.thighPlusMinus);
        thighLoss = (TextView) findViewById(R.id.thighLoss);
        armPlusMinus = (TextView) findViewById(R.id.armPlusMinus);
        armLoss = (TextView) findViewById(R.id.armLoss);

        addWeightBtn = (FloatingActionButton) findViewById(R.id.add_weight_btn);

        showProgress();

    }

    public void addWeight(View view){
        Intent intent = new Intent(this, AddDataActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showProgress();

    }

    public void showProgress(){
        Double currentWeightDB,
                currentChestDB,
                currentWaistDB,
                currentBellyDB,
                currentHipsDB,
                currentThighDB,
                currentArmDB;
        Double startWeightDB,
                startChestDB,
                startWaistDB,
                startBellyDB,
                startHipsDB,
                startThighDB,
                startArmDB;


        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String [] projection = {
                DatabaseEntry.WeightLossEntry._ID,
                DatabaseEntry.WeightLossEntry.COLUMN_DATE,
                DatabaseEntry.WeightLossEntry.COLUMN_WEIGHT,
                DatabaseEntry.WeightLossEntry.COLUMN_CHEST,
                DatabaseEntry.WeightLossEntry.COLUMN_WAIST,
                DatabaseEntry.WeightLossEntry.COLUMN_BELLY,
                DatabaseEntry.WeightLossEntry.COLUMN_HIPS,
                DatabaseEntry.WeightLossEntry.COLUMN_THIGH,
                DatabaseEntry.WeightLossEntry.COLUMN_ARM
        };



        //Getting the very first values from DB
        Cursor cursorStart = db.query(DatabaseEntry.WeightLossEntry.TABLE_NAME, projection, null, null, null, null, null);
        cursorStart.moveToFirst();

        startWeightDB = cursorStart.getDouble(cursorStart.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_WEIGHT));
        startChestDB = cursorStart.getDouble(cursorStart.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_CHEST));
        startWaistDB = cursorStart.getDouble(cursorStart.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_WAIST));
        startBellyDB = cursorStart.getDouble(cursorStart.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_BELLY));
        startHipsDB = cursorStart.getDouble(cursorStart.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_HIPS));
        startThighDB = cursorStart.getDouble(cursorStart.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_THIGH));
        startArmDB = cursorStart.getDouble(cursorStart.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_ARM));

        cursorStart.close();


        // Getting the last input values
        Cursor cursorLast = db.query(DatabaseEntry.WeightLossEntry.TABLE_NAME, projection, null, null, null, null, null);
        cursorLast.moveToLast();

        currentWeightDB = cursorLast.getDouble(cursorLast.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_WEIGHT));
        currentChestDB = cursorLast.getDouble(cursorLast.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_CHEST));
        currentWaistDB = cursorLast.getDouble(cursorLast.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_WAIST));
        currentBellyDB = cursorLast.getDouble(cursorLast.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_BELLY));
        currentHipsDB= cursorLast.getDouble(cursorLast.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_HIPS));
        currentThighDB = cursorLast.getDouble(cursorLast.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_THIGH));
        currentArmDB = cursorLast.getDouble(cursorLast.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_ARM));

        cursorLast.close();
        db.close();

        currentWeight.setText(Double.toString(currentWeightDB));
        lostWeight.setText(String.format("%.1f", Math.abs(startWeightDB - currentWeightDB)));
        weightPlusMinus.setText(plusMinus(startWeightDB, currentWeightDB));

        chestLoss.setText(String.format("%.1f", Math.abs(startChestDB - currentChestDB)));
        chestPlusMinus.setText(plusMinus(startChestDB, currentChestDB));


        waistLoss.setText(String.format("%.1f", Math.abs(startWaistDB - currentWaistDB)));
        waistPlusMinus.setText(plusMinus(startWaistDB, currentWaistDB));


        bellyLoss.setText(String.format("%.1f", Math.abs(startBellyDB - currentBellyDB)));
        bellyPlusMinus.setText(plusMinus(startBellyDB, currentBellyDB));


        hipsLoss.setText(String.format("%.1f", Math.abs(startHipsDB - currentHipsDB)));
        hipsPlusMinus.setText(plusMinus(startHipsDB, currentHipsDB));


        thighLoss.setText(String.format("%.1f", Math.abs(startThighDB - currentThighDB)));
        thighPlusMinus.setText(plusMinus(startThighDB, currentThighDB));

        armLoss.setText(String.format("%.1f", Math.abs(startArmDB - currentArmDB)));
        armPlusMinus.setText(plusMinus(startArmDB, currentArmDB));
    }


    String plusMinus(Double start, Double last){
        if(start < last){
            return "+";
        }

        return "-";
    }
}
