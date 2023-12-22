package com.example.science_museum.ui.home.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.science_museum.R;
import com.example.science_museum.ui.common.ArticleActivity;
import com.example.science_museum.ui.home.HomeViewModel;

public class NewsEntryViewHolder extends RecyclerView.ViewHolder {
    public TextView title,month_and_day,year,summary;
    public Activity mParentActivity;
    public HomeViewModel.News news;
    private LinearLayout newsEntryLayout;
    public NewsEntryViewHolder(View v, Activity parentActivity) {
        super(v);
        mParentActivity=parentActivity;
        newsEntryLayout=v.findViewById(R.id.newsEntryLayout);
        newsEntryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进入新闻详细界面
                Intent intentArticle=new Intent(mParentActivity, ArticleActivity.class);
                intentArticle.putExtra("title",news.title);
                intentArticle.putExtra("date",news.date);
                intentArticle.putExtra("content",news.content);
                mParentActivity.startActivity(intentArticle);
            }
        });
        title = v.findViewById(R.id.title);
        month_and_day=v.findViewById(R.id.month_and_day);
        summary=v.findViewById(R.id.summary);
        year=v.findViewById(R.id.year);
        Log.d("NewsEntryViewHolder",title.toString());
    }
}
