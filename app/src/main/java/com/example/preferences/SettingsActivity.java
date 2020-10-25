package com.example.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;

public class SettingsActivity extends PreferenceActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());


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

        getFragmentManager().beginTransaction().replace(android.R.id.content, new Myprefences()).commit();

    }


    public static class Myprefences extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        SharedPreferences sharedPreferences;
        SwitchPreference darkmode;
        SwitchPreference orientationpreferences;
        EditTextPreference editTextPreference;
        SharedPreferences.OnSharedPreferenceChangeListener listener;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.xml);

            darkmode = (SwitchPreference) findPreference("dark_mode");
            orientationpreferences = (SwitchPreference) findPreference("orientation_landscape");
            editTextPreference = (EditTextPreference) findPreference("status");


            editTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    String text = (String) newValue;

                    if (text.equals("")) {


                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor eits = prefs.edit();
                        eits.putString("status", editTextPreference.getText().toString());
                        eits.apply();

                    }


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    },1000);

                    return true;
                }
            });








            orientationpreferences.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    boolean isChecked = (Boolean) newValue;
                    
                    if (isChecked) {
                        
                        LandScapeisOn();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        },1000);



                    } else if (!isChecked) {
                        
                        LandScapeisOff();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        },1000);



                    }
                    


                    return true;
                }
            });
            

            darkmode.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {

                        boolean isChecked = (Boolean) newValue;

                        if (isChecked) {

                            DarkModeisOn();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            },1000);




                        } else if (!isChecked) {

                            DarkModeisOff();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            },1000);


                        }

                        return true;
                    }
                });
        }

        private void LandScapeisOff() {

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("orientation_landscape", false);
            editor.apply();

        }

        private void LandScapeisOn() {


            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("orientation_landscape", true);
            editor.apply();
        }

        public void DarkModeisOff() {


            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("orientation_landscape", false);
            editor.apply();
        }

        public void DarkModeisOn() {


            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("dark_mode", true);
            editor.apply();

        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {


            sharedPreferences.registerOnSharedPreferenceChangeListener(listener);


            editTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    String text = (String) newValue;

                    if (text.equals("")) {


                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor eits = prefs.edit();
                        eits.putString("status", editTextPreference.getText().toString());
                        eits.apply();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);



                    }


                    return true;
                }
            });




        }
    }


}


