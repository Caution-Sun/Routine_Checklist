package org.techtown.routine_checklist;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChecklistFragment extends Fragment {

    private static final String TAG = "ChecklistFragment";

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;

    TextView routine1;
    TextView routine2;
    TextView routine3;
    TextView routine4;
    TextView routine5;

    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;

    int checked1 = 0;
    int checked2 = 0;
    int checked3 = 0;
    int checked4 = 0;
    int checked5 = 0;

    Button saveButton;

    int routineCount = 5;
    ArrayList<String> routines = new ArrayList<>();
    ArrayList<Boolean> checks = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();

        for(int i = 1; i <= 5; i++){
            setLayout(i, View.VISIBLE);
            setRoutines(i, "");
            setCheck(i,false);
        }

        for(int i = 1; i <= routines.size(); i++){
            setRoutines(i, routines.get(i-1));
        }

        for(int i = 1; i <= checks.size(); i++){
            setCheck(i, checks.get(i-1));
        }

        for(int i = 5; i > routineCount; i--){
            setLayout(i, View.GONE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        checks.clear();
        for(int i = 1; i <= routineCount; i++){
            saveChecks(i);
        }

        MainActivity activity = (MainActivity) getActivity();
        activity.getChecked();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_checklist, container, false);

        layout1 = rootview.findViewById(R.id.layoutRoutine1);
        layout2 = rootview.findViewById(R.id.layoutRoutine2);
        layout3 = rootview.findViewById(R.id.layoutRoutine3);
        layout4 = rootview.findViewById(R.id.layoutRoutine4);
        layout5 = rootview.findViewById(R.id.layoutRoutine5);

        routine1 = rootview.findViewById(R.id.textRoutine1);
        routine2 = rootview.findViewById(R.id.textRoutine2);
        routine3 = rootview.findViewById(R.id.textRoutine3);
        routine4 = rootview.findViewById(R.id.textRoutine4);
        routine5 = rootview.findViewById(R.id.textRoutine5);

        checkBox1 = rootview.findViewById(R.id.checkBoxRoutine1);
        checkBox2 = rootview.findViewById(R.id.checkBoxRoutine2);
        checkBox3 = rootview.findViewById(R.id.checkBoxRoutine3);
        checkBox4 = rootview.findViewById(R.id.checkBoxRoutine4);
        checkBox5 = rootview.findViewById(R.id.checkBoxRoutine5);

        saveButton = rootview.findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoutine();
            }
        });

        return rootview;
    }

    private void setLayout(int a, int view){
        if(view == View.GONE){
            switch (a){
                case 1:
                    layout1.setVisibility(View.GONE);
                    break;
                case 2:
                    layout2.setVisibility(View.GONE);
                    break;
                case 3:
                    layout3.setVisibility(View.GONE);
                    break;
                case 4:
                    layout4.setVisibility(View.GONE);
                    break;
                case 5:
                    layout5.setVisibility(View.GONE);
                    break;
            }
        }else if(view == View.VISIBLE){
            switch (a){
                case 1:
                    layout1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    layout2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    layout3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    layout4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    layout5.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void setRoutines(int a, String s){
        switch (a){
            case 1:
                routine1.setText(s);
                break;
            case 2:
                routine2.setText(s);
                break;
            case 3:
                routine3.setText(s);
                break;
            case 4:
                routine4.setText(s);
                break;
            case 5:
                routine5.setText(s);
                break;
        }
    }

    private void setCheck(int a, boolean c){
        switch (a){
            case 1:
                if(c)
                    checkBox1.setChecked(true);
                else
                    checkBox1.setChecked(false);
                break;
            case 2:
                if(c)
                    checkBox2.setChecked(true);
                else
                    checkBox2.setChecked(false);
                break;
            case 3:
                if(c)
                    checkBox3.setChecked(true);
                else
                    checkBox3.setChecked(false);
                break;
            case 4:
                if(c)
                    checkBox4.setChecked(true);
                else
                    checkBox4.setChecked(false);
                break;
            case 5:
                if(c)
                    checkBox5.setChecked(true);
                else
                    checkBox5.setChecked(false);
                break;
        }
    }

    private void saveChecks(int a){
        switch (a){
            case 1:
                checks.add(checkBox1.isChecked());
                break;
            case 2:
                checks.add(checkBox2.isChecked());
                break;
            case 3:
                checks.add(checkBox3.isChecked());
                break;
            case 4:
                checks.add(checkBox4.isChecked());
                break;
            case 5:
                checks.add(checkBox5.isChecked());
                break;
        }
    }

    private void addRoutine(){
        for(int i = 1; i <= 5; i++){
            setChecked(i);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateNow = simpleDateFormat.format(System.currentTimeMillis());

        String sql = "select * from " + RoutineDatabase.TABLE_ROUTINE + " where DATE = '" + dateNow + "'";

        RoutineDatabase database = RoutineDatabase.getInstance(getContext());

        if(database != null){
            Cursor cursor = database.rawQuery(sql);

            if(cursor.getCount() != 0){
                sql = "update " + RoutineDatabase.TABLE_ROUTINE + " set " +
                "COUNT = '" + routineCount + "', " +
                "ROUTINE1 = '" + routine1.getText().toString() + "', " +
                "ROUTINE2 = '" + routine2.getText().toString() + "', " +
                "ROUTINE3 = '" + routine3.getText().toString() + "', " +
                "ROUTINE4 = '" + routine4.getText().toString() + "', " +
                "ROUTINE5 = '" + routine5.getText().toString() + "', " +
                "CHECK1 = " + checked1 + ", " +
                "CHECK2 = " + checked2 + ", " +
                "CHECK3 = " + checked3 + ", " +
                "CHECK4 = " + checked4 + ", " +
                "CHECK5 = " + checked5 + " " +
                "where DATE = '" + dateNow + "'";
            }else{
                sql = "insert into " + RoutineDatabase.TABLE_ROUTINE +
                "(DATE, COUNT, ROUTINE1, ROUTINE2, ROUTINE3, ROUTINE4, ROUTINE5, CHECK1, CHECK2, CHECK3, CHECK4, CHECK5) values(" +
                "'" + dateNow + "', " +
                "" + routineCount + ", " +
                "'" + routine1.getText().toString() + "', " +
                "'" + routine2.getText().toString() + "', " +
                "'" + routine3.getText().toString() + "', " +
                "'" + routine4.getText().toString() + "', " +
                "'" + routine5.getText().toString() + "', " +
                 checked1 + ", " +
                 checked2 + ", " +
                 checked3 + ", " +
                 checked4 + ", " +
                 checked5 + ")";
            }

            database.execSQL(sql);

            Toast.makeText(getContext(), "달성 데이터 추가됨", Toast.LENGTH_SHORT).show();
        }
    }

    private void setChecked(int a){
        switch (a){
            case 1:
                if(checkBox1.isChecked())
                    checked1 = 1;
                else
                    checked1 = 0;
                break;
            case 2:
                if(checkBox2.isChecked())
                    checked2 = 1;
                else
                    checked2 = 0;
                break;
            case 3:
                if(checkBox3.isChecked())
                    checked3 = 1;
                else
                    checked3 = 0;
                break;
            case 4:
                if(checkBox4.isChecked())
                    checked4 = 1;
                else
                    checked4 = 0;
                break;
            case 5:
                if(checkBox5.isChecked())
                    checked5 = 1;
                else
                    checked5 = 0;
                break;
        }
    }
}