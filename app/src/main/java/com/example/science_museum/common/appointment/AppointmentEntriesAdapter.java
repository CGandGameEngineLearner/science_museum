package com.example.science_museum.common.appointment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science_museum.R;
import com.example.science_museum.common.data.AppointmentBean;
import com.example.science_museum.common.data.NewsRepository;
import com.example.science_museum.common.data.dao.Appointment;

import java.util.List;


public class AppointmentEntriesAdapter extends RecyclerView.Adapter<AppointmentEntryHolder> {
    private List<AppointmentBean> mDataset;
    private Activity mParentActivity;


    // Provide a suitable constructor (depends on the kind of dataset)
    public AppointmentEntriesAdapter(List<AppointmentBean> myDataset, Activity parentActivity) {
        mDataset = myDataset;
        mParentActivity=parentActivity;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public AppointmentEntryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use LayoutInflater to correctly handle the layout parameters
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_entry, parent, false);
        AppointmentEntryHolder vh = new AppointmentEntryHolder(v,mParentActivity);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull AppointmentEntryHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(mDataset.get(position).getBookerName());
        String[] dateArray = mDataset.get(position).getAppointmentDate().split("-");
        holder.month_and_day.setText(dateArray[1]+"/"+dateArray[2]);
        holder.year.setText(dateArray[0]);
        holder.summary.setText(mDataset.get(position).getIdNumber());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
