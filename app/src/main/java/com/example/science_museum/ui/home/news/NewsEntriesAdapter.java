package com.example.science_museum.ui.home.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science_museum.R;

import com.example.science_museum.ui.home.HomeViewModel;

import java.util.List;



public class NewsEntriesAdapter extends RecyclerView.Adapter<NewsEntryViewHolder> {
    private List<HomeViewModel.News> mDataset;
    private Activity mParentActivity;


    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsEntriesAdapter(List<HomeViewModel.News> myDataset, Activity parentActivity) {
        mDataset = myDataset;
        mParentActivity=parentActivity;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public NewsEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use LayoutInflater to correctly handle the layout parameters
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_entry, parent, false);
        NewsEntryViewHolder vh = new NewsEntryViewHolder(v,mParentActivity);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull NewsEntryViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(mDataset.get(position).title);
        String[] dateArray = mDataset.get(position).date.split("-");
        holder.month_and_day.setText(dateArray[1]+"/"+dateArray[2]);
        holder.year.setText(dateArray[0]);
        holder.summary.setText(mDataset.get(position).summary);
        holder.news=mDataset.get(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
