package com.example.science_museum.common;

import androidx.appcompat.app.AppCompatActivity;
import com.example.science_museum.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ArticleActivity extends AppCompatActivity {
    TextView titleTextView,dateTextView,contentTextView;
    Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        titleTextView=findViewById(R.id.title);
        dateTextView=findViewById(R.id.date);
        contentTextView=findViewById(R.id.content);
        mIntent=getIntent();
        setTitle(mIntent.getStringExtra("title"));
        setDate(mIntent.getStringExtra("date"));
        setContent(mIntent.getStringExtra("content"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setTitle(String s)
    {
        titleTextView.setText(s);
    }
    public void setDate(String s)
    {
        dateTextView.setText(s);
    }
    public void setContent(String s)
    {
        contentTextView.setText(s);
    }
}
