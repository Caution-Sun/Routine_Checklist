package org.techtown.routine_checklist;

import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

public class CalenderFragment extends Fragment {

    String date = "";
    int routineCount = 0;
    String Routine1 = "";
    String Routine2 = "";
    String Routine3 = "";
    String Routine4 = "";
    String Routine5 = "";
    int checked1 = 0;
    int checked2 = 0;
    int checked3 = 0;
    int checked4 = 0;
    int checked5 = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_calander, container, false);

        CalendarView calendar = rootview.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + month + "-" + dayOfMonth;
                String sql = "select * from " + RoutineDatabase.TABLE_ROUTINE + " where DATE = " + date;

                RoutineDatabase database = RoutineDatabase.getInstance(getContext());
                Cursor cursor = database.rawQuery(sql);

                if(cursor.getCount() != 0){
                    cursor.moveToNext();

                    routineCount = cursor.getInt(1);
                    Routine1 = cursor.getString(2);
                    Routine2 = cursor.getString(3);
                    Routine3 = cursor.getString(4);
                    Routine4 = cursor.getString(5);
                    Routine5 = cursor.getString(6);
                    checked1 = cursor.getInt(7);
                    checked2 = cursor.getInt(8);
                    checked3 = cursor.getInt(9);
                    checked4 = cursor.getInt(10);
                    checked5 = cursor.getInt(11);
                }
            }
        });

        return rootview;
    }
}