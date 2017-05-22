package com.limore.zhibek.weightlosstracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.limore.zhibek.weightlosstracker.MainActivity;

import com.limore.zhibek.weightlosstracker.Data.DBHelper;
import com.limore.zhibek.weightlosstracker.Data.DatabaseEntry;

import static android.app.PendingIntent.getActivity;

public class AddDataActivity extends AppCompatActivity {

    EditText weight,
            chest,
            waist,
            belly,
            hips,
            thigh,
            arm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        weight = (EditText) findViewById(R.id.weight);
        chest = (EditText) findViewById(R.id.chest);
        waist = (EditText) findViewById(R.id.waist);
        belly = (EditText) findViewById(R.id.belly);
        hips = (EditText) findViewById(R.id.hips);
        thigh = (EditText) findViewById(R.id.thigh);
        arm = (EditText) findViewById(R.id.arm);

        displayLastInput();
    }

    public void displayLastInput(){
        Double weightDB, chestDB, waistDB, bellyDB, hipsDB, thighDB, armDB;

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

        Cursor cursor = db.query(DatabaseEntry.WeightLossEntry.TABLE_NAME, projection, null, null, null, null, null);
        cursor.moveToLast();
        weightDB = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_WEIGHT));
        chestDB = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_CHEST));
        waistDB = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_WAIST));
        bellyDB = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_BELLY));
        hipsDB = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_HIPS));
        thighDB = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_THIGH));
        armDB = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseEntry.WeightLossEntry.COLUMN_ARM));

        weight.setText(Double.toString(weightDB));
        chest.setText(Double.toString(chestDB));
        waist.setText(Double.toString(waistDB));
        belly.setText(Double.toString(bellyDB));
        hips.setText(Double.toString(hipsDB));
        thigh.setText(Double.toString(thighDB));
        arm.setText(Double.toString(armDB));


        cursor.close();
        db.close();
    }


    public void submitData(View view){
        String weightData = weight.getText().toString();
        String chestData = chest.getText().toString();
        String waistData = waist.getText().toString();
        String bellyData = belly.getText().toString();
        String hipsData = hips.getText().toString();
        String thighData = thigh.getText().toString();
        String armData = arm.getText().toString();
        long date = System.currentTimeMillis();

        // inserting data to DB
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseEntry.WeightLossEntry.COLUMN_DATE, date);
        values.put(DatabaseEntry.WeightLossEntry.COLUMN_WEIGHT, weightData);
        values.put(DatabaseEntry.WeightLossEntry.COLUMN_CHEST, chestData);
        values.put(DatabaseEntry.WeightLossEntry.COLUMN_WAIST, waistData);
        values.put(DatabaseEntry.WeightLossEntry.COLUMN_BELLY, bellyData);
        values.put(DatabaseEntry.WeightLossEntry.COLUMN_HIPS, hipsData);
        values.put(DatabaseEntry.WeightLossEntry.COLUMN_THIGH, thighData);
        values.put(DatabaseEntry.WeightLossEntry.COLUMN_ARM, armData);

        db.insert(DatabaseEntry.WeightLossEntry.TABLE_NAME, null, values);
        db.close();

        Intent intent = new Intent();
        setResult(MainActivity.RESULT_CANCELED, intent);
        finish();
    }
}
