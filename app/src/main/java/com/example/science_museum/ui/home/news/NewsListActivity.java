package com.example.science_museum.ui.home.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science_museum.ui.home.HomeFragment.NewsEntriesAdapter;
import com.example.science_museum.ui.home.HomeViewModel;
import android.os.Bundle;
import com.example.science_museum.R;

public class NewsListActivity extends AppCompatActivity {

    private RecyclerView mNewsRecyclerView;
    private HomeViewModel mViewModel;

    private void initNews()
    {
        mNewsRecyclerView=findViewById(R.id.news_recycler_view);
        //mNewsRecyclerView.setAdapter(new NewsEntriesAdapter(mViewModel.getAllNews()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNewsRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
