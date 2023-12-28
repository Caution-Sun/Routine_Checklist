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
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class CalenderFragment extends Fragment {

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

    TextView textStats;
    MaterialCalendarView calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_calander, container, false);

        // View 참조하기
        textStats = rootview.findViewById(R.id.textStats);
        calendar = rootview.findViewById(R.id.calendarView);

        // 달력의 날짜 선택 시 그날 저장된 루틴 데이터 dialog로 띄우기
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                
                // 년 월 일을 받아 해당 날짜의 데이터를 가져오는 SQL문 만들기
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDay();

                String monthString = "";
                String dayString = "";

                if(month < 10)
                    monthString = "0" + month;
                else
                    monthString += month;
                if(day < 10)
                    dayString = "0" + day;
                else
                    dayString += day;

                String calendarDate = year + "-" + monthString + "-" + dayString;

                String sql = "select * from " + RoutineDatabase.TABLE_ROUTINE + " where DATE = '" + calendarDate + "'";
                
                // 데이터베이스 싱글톤 객체 만들기
                RoutineDatabase database = RoutineDatabase.getInstance(getContext());
                // 데이터를 가져오는 sql 실행
                Cursor cursor = database.rawQuery(sql);

                // 해당 날짜에 저장된 데이터가 있다면
                if(cursor.getCount() != 0){
                    // 전역 변수에 루틴 데이터 받아 저장하기
                    setRoutine(cursor);
                    // 대화상자로 루틴 데이터 띄우기
                    showRoutine(year, month, day, true);
                }else
                    // 저장된 데이터가 없다면 저장된 데이터가 없다고 대화상자로 띄우기
                    showRoutine(year, month, day, false);
            }
        });
        
        // 달력의 달을 바꿨을 때 통계 보여주기
        calendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

                // 년 월을 받아 해당 달의 처음부터 끝까지의 데이터를 검색하는 SQL문 만들기.
                int year = date.getYear();
                int month = date.getMonth();
                String monthString = "";

                if(month < 10)
                    monthString = "0" + month;
                else
                    monthString += month;

                String calendarDateStart = year + "-" + monthString + "-" + "01";
                String calendarDateEnd = year + "-" + monthString + "-";

                switch (month){
                    case 1 : case 3 : case 5 : case 7 : case 8 : case 10 : case 12 :
                        calendarDateEnd += "31";
                        break;
                    case 2 :
                        calendarDateEnd += "28";
                        break;
                    case 4 : case 6 : case 9 : case 11:
                        calendarDateEnd += "30";
                        break;
                }

                String sql = "select * from " + RoutineDatabase.TABLE_ROUTINE + " where DATE between '" + calendarDateStart  + "' and '" + calendarDateEnd + "'";
                // 데이터베이스 싱글톤 객체 만들기
                RoutineDatabase database = RoutineDatabase.getInstance(getContext());
                // 데이터를 가져오는 SQL문 실행
                Cursor cursor = database.rawQuery(sql);

                // 통계 데이터 초기화
                dayCount = 0;
                allRoutine = 0;
                successRoutine = 0;
                failedRoutine = 0;
                String stats = "";

                // 통계 데이터를 저장하고 띄우는 메소드 실행
                setStats(cursor, year, month);
            }
        });

        return rootview;
    }

    private void setRoutine(Cursor cursor){
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

    private void setStats(Cursor cursor, int year, int month){
        dayCount = 0;
        allRoutine = 0;
        successRoutine = 0;
        failedRoutine = 0;

        String stats = "";

        if(cursor.getCount() != 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                dayCount++;

                int routineCount = cursor.getInt(1);

                if(routineCount != 0){
                    allRoutine += routineCount;

                    int start = 7;
                    int end = start +  routineCount;

                    for(int j = start; j < end; j++){
                        int checked = cursor.getInt(j);
                        if(checked == 1)
                            successRoutine++;
                        else
                            failedRoutine++;
                    }
                }
            }

            double successRate = (double)successRoutine / (double)allRoutine * 100.0;
            String successRateString = String.format("%.2f", successRate);

            stats = year + "년 " + month + "월의 통계\n저장 일 수 : " + dayCount + "\n저장 루틴 수 : " + allRoutine + "\n루틴 달성률 : " + successRateString + "%";

        }else{
            stats = year + "년 " + month + "월엔 데이터가 없습니다.";
        }

        textStats.setText(stats);
    }
}