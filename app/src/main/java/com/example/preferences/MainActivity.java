package com.example.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    SettingsActivity.Myprefences myprefences;

    TextView helloworld;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());


        if (sharedPreferences.getBoolean("dark_mode", true)) {

            setTheme(R.style.NightTheme);
        } else {
            setTheme(R.style.MainTheme);

        }

        if (sharedPreferences.getBoolean("orientation_landscape", true)) {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        } else {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }

        setContentView(R.layout.activity_main);

        String text = sharedPreferences.getString("status", "");

        Toast.makeText(this, "message from edit text " + text, Toast.LENGTH_SHORT).show();




        String message = String.valueOf(sharedPreferences.getBoolean("dark_mode", true));

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.settings) {

            startActivity(new Intent(MainActivity.this, SettingsActivity.class));


        }
        return super.onOptionsItemSelected(item);
    }



}