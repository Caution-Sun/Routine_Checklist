package org.techtown.routine_checklist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static RoutineDatabase mDatabase = null;

    ChecklistFragment checklistFragment;
    CalenderFragment calenderFragment;
    SettingFragment settingFragment;

    int routineCount = 5;
    ArrayList<String> routines = new ArrayList<>();
    ArrayList<Boolean> checks = new ArrayList<>();

    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDatabase();

        tinyDB = new TinyDB(this);

        checklistFragment = new ChecklistFragment();
        calenderFragment = new CalenderFragment();
        settingFragment = new SettingFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, checklistFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.tab1:
                        setRoutines(routineCount, routines, checklistFragment, checks);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, checklistFragment).commit();
                        return true;
                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, calenderFragment).commit();
                        return true;
                    case R.id.tab3:
                        setRoutines(routineCount, routines, settingFragment, checks);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        checks.clear();
        checks.addAll(checklistFragment.checks);

        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();

        restoreState();

        setRoutines(routineCount, routines, checklistFragment, checks);
        setRoutines(routineCount, routines, settingFragment, checks);
    }

    public void getRoutines(int num, ArrayList<String> routines){
        routineCount = num;

        this.routines.clear();
        this.routines.addAll(routines);

        this.checks.clear();

        setRoutines(routineCount, routines, checklistFragment, checks);
        setRoutines(routineCount, routines, settingFragment, checks);
    }

    public void getChecked(){
        checks.clear();

        checks.addAll(checklistFragment.checks);
    }

    public void setRoutines(int num, ArrayList<String> routines, Fragment fragment, ArrayList<Boolean> checks){
        if(fragment == checklistFragment){
            checklistFragment.routineCount = num;
            checklistFragment.routines.clear();
            checklistFragment.routines.addAll(routines);
            checklistFragment.checks.clear();
            checklistFragment.checks.addAll(checks);
        }else if(fragment == settingFragment){
            settingFragment.routineCount = num;
            settingFragment.routines.clear();
            settingFragment.routines.addAll(routines);
        }
    }

    protected void saveState(){
        tinyDB.putInt("routineCount", routineCount);
        tinyDB.putListString("routines", routines);
        tinyDB.putListBoolean("checks", checks);
    }

    protected void restoreState(){
        routineCount = tinyDB.getInt("routineCount");

        routines.clear();
        routines.addAll(tinyDB.getListString("routines"));

        checks.clear();
        checks.addAll(tinyDB.getListBoolean("checks"));
    }

    public void openDatabase(){
        if(mDatabase != null){
            mDatabase.close();
            mDatabase = null;
        }

        mDatabase = RoutineDatabase.getInstance(this);
        boolean isOpen = mDatabase.open();
        if(isOpen)
            Log.d(TAG, "Routine database is open.");
        else
            Log.d(TAG, "Rotine database is not open.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mDatabase != null){
            mDatabase.close();
            mDatabase = null;
        }
    }
}