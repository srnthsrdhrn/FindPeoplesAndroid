package com.blackpanther.findpeople.NewProject;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blackpanther.findpeople.R;

import java.util.Date;
import java.util.zip.Inflater;

/**
 * Created by singapore on 25-09-2016.
 */
public class NewProjectFragment2 extends Fragment {
    private Button startDate,EndDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_new_project_2, container, false);
        startDate = (Button) v.findViewById(R.id.start_date);
        EndDate = (Button) v.findViewById(R.id.end_date);
        final Date date1 = new Date();
            startDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
                        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                date1.setDate(datePicker.getDayOfMonth());
                                date1.setMonth(datePicker.getMonth());
                                date1.setYear(datePicker.getYear());
                                date1.setSeconds(0);
                                date1.setMinutes(0);
                                date1.setHours(0);
                            }
                        });
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        LinearLayout linearLayout = new LinearLayout(getContext());
                        linearLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        final View view1 = LayoutInflater.from(getContext()).inflate(R.layout.new_project_fragment_alert_dialog,linearLayout);
                        builder.setView(view1);

                        builder.setPositiveButton("", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatePicker datePicker = (DatePicker) view1.findViewById(R.id.datePicker);
                                int date=datePicker.getDayOfMonth();
                                int month = datePicker.getMonth();
                                int year = datePicker.getYear();
                                date1.setDate(date);
                                date1.setMonth(month);
                                date1.setYear(year);
                                date1.setSeconds(0);
                                date1.setMinutes(0);
                                date1.setHours(0);
                            }
                        });

                        Toast.makeText(getContext(),date1+"",Toast.LENGTH_LONG).show();
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });

        return v;
    }
}
