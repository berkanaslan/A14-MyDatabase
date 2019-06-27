package com.berkanaslan.mydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    try {

        SQLiteDatabase myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR, age INT(2))");

       /* myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('James', 35)");
        myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Kirk', 25)");
        myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars', 30)"); */

        /* myDatabase.execSQL("DELETE FROM musicians WHERE age > 10"); */

        myDatabase.execSQL("UPDATE musicians SET age = 40 WHERE name='Lars'");


        Cursor cursor = myDatabase.rawQuery("SELECT * FROM musicians",null);

        int nameIx = cursor.getColumnIndex("name");
        int ageIx = cursor.getColumnIndex("age");

        cursor.moveToFirst();

        while (cursor != null) {
            System.out.println("Name: " + cursor.getString(nameIx));
            System.out.println("Age: " + cursor.getInt(ageIx));

            cursor.moveToNext();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }



    }
}
