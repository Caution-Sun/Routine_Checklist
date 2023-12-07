package org.techtown.routine_checklist;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ChecklistFragment extends Fragment {

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

    Button saveButton;

    int routineCount = 5;
    ArrayList<String> routines = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();

        for(int i = 1; i <= 5; i++){
            setLayout(i, View.VISIBLE);
            setRoutines(i, "");
        }

        for(int i = 1; i <= routines.size(); i++){
            setRoutines(i, routines.get(i-1));
        }

        for(int i = 5; i > routineCount; i--){
            setLayout(i, View.GONE);
        }
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
}