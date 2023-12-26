package org.techtown.routine_checklist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class CalenderFragment extends Fragment {

    String calendarDate = "";
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

    int dayCount = 0;
    int allRoutine = 0;
    int successRoutine = 0;
    int failedRoutine = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_calander, container, false);

        TextView textStats = rootview.findViewById(R.id.textStats);

        MaterialCalendarView calender = rootview.findViewById(R.id.calendarView);
        calender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDay();
                calendarDate = year + "-" + month + "-" + day;

                String sql = "select * from " + RoutineDatabase.TABLE_ROUTINE + " where DATE = " + calendarDate;

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

                    showRoutine(year, month, day, true);
                }else
                    showRoutine(year, month, day, false);
            }
        });
        calender.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                int year = date.getYear();
                int month = date.getMonth();
                calendarDate = year + "-" + month + "-" + "**";

                String sql = "select * from " + RoutineDatabase.TABLE_ROUTINE + " where DATE = " + calendarDate;

                dayCount = 0;
                allRoutine = 0;
                successRoutine = 0;
                failedRoutine = 0;

                String stats = "";

                RoutineDatabase database = RoutineDatabase.getInstance(getContext());
                Cursor cursor = database.rawQuery(sql);

                if(cursor.getCount() != 0) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        cursor.moveToNext();

                        dayCount++;

                        setStats(cursor);
                    }

                    stats = year + "년 " + month + "월의 통계.\n";

                }else{
                    stats = year + "년 " + month + "월엔 데이터가 없습니다.";
                }

                textStats.setText(stats);
            }
        });

        return rootview;
    }


    private void showRoutine(int year, int month, int dayOfMonth, boolean a){
        String date = year + " " + month + " " + dayOfMonth;
        String content = "";

        if(a){
            switch (routineCount){
                case 1:
                    content = content + Routine1 + " : ";
                    if(checked1 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    break;
                case 2:
                    content = content + Routine1 + " : ";
                    if(checked1 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    content = content + "\n" + Routine2 + " : ";
                    if(checked2 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    break;
                case 3:
                    content = content + Routine1 + " : ";
                    if(checked1 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    content = content + "\n" + Routine2 + " : ";
                    if(checked2 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    content = content + "\n" + Routine3 + " : ";
                    if(checked3 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    break;
                case 4:
                    content = content + Routine1 + " : ";
                    if(checked1 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    content = content + "\n" + Routine2 + " : ";
                    if(checked2 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    content = content + "\n" + Routine3 + " : ";
                    if(checked3 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    content = content + "\n" + Routine4 + " : ";
                    if(checked4 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    break;
                case 5:
                    content = content + Routine1 + " : ";
                    if(checked1 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    content = content + "\n" + Routine2 + " : ";
                    if(checked2 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    content = content + "\n" + Routine3 + " : ";
                    if(checked3 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    content = content + "\n" + Routine4 + " : ";
                    if(checked4 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";

                    content = content + "\n" + Routine5 + " : ";
                    if(checked5 == 1)
                        content = content + "달성";
                    else
                        content = content + "미달성";
                    break;
            }
        }else{
            content = "저장된 루틴 데이터가 없는 날입니다.";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(date);
        builder.setMessage(content);

        builder.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setStats(Cursor cursor){

        int routineCount = cursor.getInt(1);

        if(routineCount != 0){
            allRoutine += routineCount;

            int start = 7;
            int end = start +  routineCount;

            for(int i = start; i < end; i++){
                int checked = cursor.getInt(i);
                if(checked == 1)
                    successRoutine++;
                else
                    failedRoutine++;
            }
        }
    }
}