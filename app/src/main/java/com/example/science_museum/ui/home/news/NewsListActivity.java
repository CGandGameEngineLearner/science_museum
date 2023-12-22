package com.example.science_museum.ui.home.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science_museum.ui.home.news.NewsEntriesAdapter;
import com.example.science_museum.ui.home.HomeViewModel;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.science_museum.R;

public class NewsListActivity extends AppCompatActivity {

    private RecyclerView mNewsRecyclerView;
    private HomeViewModel mViewModel;

    private void initNews()
    {
        mViewModel= ViewModelProviders.of(this).get(HomeViewModel.class);
        mNewsRecyclerView=findViewById(R.id.news_recycler_view);
        mNewsRecyclerView.setAdapter(new NewsEntriesAdapter(mViewModel.getAllNews(),this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNewsRecyclerView.setLayoutManager(layoutManager);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initNews();
    }
}
