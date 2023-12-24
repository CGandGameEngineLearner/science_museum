package com.example.science_museum.common.appointment;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.science_museum.R;
import com.example.science_museum.common.ArticleActivity;
import com.example.science_museum.common.data.AppointmentBean;
import com.example.science_museum.common.data.NewsRepository;
import com.example.science_museum.common.data.dao.Appointment;

public class AppointmentEntryHolder extends RecyclerView.ViewHolder {
    public TextView title,month_and_day,year,summary;
    public Activity mParentActivity;
    public AppointmentBean appointmentBean;

    public AppointmentEntryHolder(View v, Activity parentActivity) {
        super(v);
        mParentActivity=parentActivity;

        title = v.findViewById(R.id.title);
        month_and_day=v.findViewById(R.id.month_and_day);
        summary=v.findViewById(R.id.summary);
        year=v.findViewById(R.id.year);
        Log.d("NewsEntryViewHolder",title.toString());
    }
}
