package org.techtown.routine_checklist;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingFragment extends Fragment {

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;

    Button minusButton1;
    Button minusButton2;
    Button minusButton3;
    Button minusButton4;
    Button minusButton5;

    Button plusButton;

    Button settingButton;

    int routineCount = 5;
    ArrayList<String> routines = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();

        for(int i = 1; i <= 5; i++){
            setLayout(i, View.VISIBLE);
            setEditRoutines(i, "");
        }

        for(int i = 1; i <= routines.size(); i++){
            setEditRoutines(i, routines.get(i-1));
        }

        for(int i = 5; i > routineCount; i--){
            setLayout(i, View.GONE);
        }

        plusButtonVisibility();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_setting, container, false);

        layout1 = rootView.findViewById(R.id.layoutChange1);
        layout2 = rootView.findViewById(R.id.layoutChange2);
        layout3 = rootView.findViewById(R.id.layoutChange3);
        layout4 = rootView.findViewById(R.id.layoutChange4);
        layout5 = rootView.findViewById(R.id.layoutChange5);

        editText1 = rootView.findViewById(R.id.editRoutine1);
        editText2 = rootView.findViewById(R.id.editRoutine2);
        editText3 = rootView.findViewById(R.id.editRoutine3);
        editText4 = rootView.findViewById(R.id.editRoutine4);
        editText5 = rootView.findViewById(R.id.editRoutine5);

        minusButton1 = rootView.findViewById(R.id.buttonMinus1);
        minusButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp;
                switch (routineCount){
                    case 1:
                        layout1.setVisibility(View.GONE);
                        break;
                    case 2:
                        layout2.setVisibility(View.GONE);

                        tmp = editText2.getText().toString();
                        for(int i = routineCount; i > 1; i--){
                            tmp = deleteRoutine(i, tmp);
                        }
                        break;
                    case 3:
                        layout3.setVisibility(View.GONE);

                        tmp = editText3.getText().toString();
                        for(int i = routineCount; i > 1; i--){
                            tmp = deleteRoutine(i, tmp);
                        }
                        break;
                    case 4:
                        layout4.setVisibility(View.GONE);

                        tmp = editText4.getText().toString();
                        for(int i = routineCount; i > 1; i--){
                            tmp = deleteRoutine(i, tmp);
                        }
                        break;
                    case 5:
                        layout5.setVisibility(View.GONE);

                        tmp = editText5.getText().toString();
                        for(int i = routineCount; i > 1; i--){
                            tmp = deleteRoutine(i, tmp);
                        }
                        break;
                }

                routineCount--;
                plusButtonVisibility();
            }
        });
        minusButton2 = rootView.findViewById(R.id.buttonMinus2);
        minusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp;
                switch (routineCount){
                    case 2:
                        layout2.setVisibility(View.GONE);
                        break;
                    case 3:
                        layout3.setVisibility(View.GONE);

                        tmp = editText3.getText().toString();
                        for(int i = routineCount; i > 2; i--){
                            tmp = deleteRoutine(i, tmp);
                        }
                        break;
                    case 4:
                        layout4.setVisibility(View.GONE);

                        tmp = editText4.getText().toString();
                        for(int i = routineCount; i > 2; i--){
                            tmp = deleteRoutine(i, tmp);
                        }
                        break;
                    case 5:
                        layout5.setVisibility(View.GONE);

                        tmp = editText5.getText().toString();
                        for(int i = routineCount; i > 2; i--){
                            tmp = deleteRoutine(i, tmp);
                        }
                        break;
                }

                routineCount--;
                plusButtonVisibility();
            }
        });
        minusButton3 = rootView.findViewById(R.id.buttonMinus3);
        minusButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp;
                switch (routineCount){
                    case 3:
                        layout3.setVisibility(View.GONE);
                        break;
                    case 4:
                        layout4.setVisibility(View.GONE);

                        tmp = editText4.getText().toString();
                        for(int i = routineCount; i > 3; i--){
                            tmp = deleteRoutine(i, tmp);
                        }
                        break;
                    case 5:
                        layout5.setVisibility(View.GONE);

                        tmp = editText5.getText().toString();
                        for(int i = routineCount; i > 3; i--){
                            tmp = deleteRoutine(i, tmp);
                        }
                        break;
                }

                routineCount--;
                plusButtonVisibility();
            }
        });
        minusButton4 = rootView.findViewById(R.id.buttonMinus4);
        minusButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp;
                switch (routineCount){
                    case 4:
                        layout4.setVisibility(View.GONE);
                        break;
                    case 5:
                        layout5.setVisibility(View.GONE);

                        tmp = editText5.getText().toString();
                        for(int i = routineCount; i > 4; i--){
                            tmp = deleteRoutine(i, tmp);
                        }
                        break;
                }

                routineCount--;
                plusButtonVisibility();
            }
        });
        minusButton5 = rootView.findViewById(R.id.buttonMinus5);
        minusButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout5.setVisibility(View.GONE);
                routineCount--;
                plusButtonVisibility();
            }
        });

        plusButton = rootView.findViewById(R.id.buttonPlus);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(routineCount < 5){
                    switch (routineCount){
                        case 0:
                            editText1.setText(null);
                            layout1.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            editText2.setText(null);
                            layout2.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            editText3.setText(null);
                            layout3.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            editText4.setText(null);
                            layout4.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            editText5.setText(null);
                            layout5.setVisibility(View.VISIBLE);
                            break;
                    }
                    routineCount++;
                    plusButtonVisibility();
                }
            }
        });

        settingButton = rootView.findViewById(R.id.buttonSetting);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                routines.clear();
                for(int i = 1; i <= routineCount; i++){
                    setRoutines(i);
                }

                MainActivity activity = (MainActivity) getActivity();
                activity.getRoutines(routineCount, routines);

                Toast.makeText(rootView.getContext(),"설정 저장됨", Toast.LENGTH_SHORT).show();
            }
        });

        plusButtonVisibility();

        return rootView;
    }

    private void plusButtonVisibility(){
        if(routineCount == 5){
            plusButton.setVisibility(View.GONE);
        }else{
            plusButton.setVisibility(View.VISIBLE);
        }
    }

    private String deleteRoutine(int a, String tmp){

        String next = "";
        switch (a){
            case 1:
                break;
            case 2:
                next = editText1.getText().toString();
                editText1.setText(tmp);
                break;
            case 3:
                next = editText2.getText().toString();
                editText2.setText(tmp);
                break;
            case 4:
                next = editText3.getText().toString();
                editText3.setText(tmp);
                break;
            case 5:
                next = editText4.getText().toString();
                editText4.setText(tmp);
                break;
        }

        return next;
    }

    private void setRoutines(int a){
        switch (a){
            case 1:
                routines.add(editText1.getText().toString());
                break;
            case 2:
                routines.add(editText2.getText().toString());
                break;
            case 3:
                routines.add(editText3.getText().toString());
                break;
            case 4:
                routines.add(editText4.getText().toString());
                break;
            case 5:
                routines.add(editText5.getText().toString());
                break;
        }
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

    private void setEditRoutines(int a, String s){
        switch (a){
            case 1:
                editText1.setText(s);
                break;
            case 2:
                editText2.setText(s);
                break;
            case 3:
                editText3.setText(s);
                break;
            case 4:
                editText4.setText(s);
                break;
            case 5:
                editText5.setText(s);
                break;
        }
    }

}