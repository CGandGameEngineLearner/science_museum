package com.example.science_museum.common.appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.science_museum.R;
import com.example.science_museum.common.user.LoginActivity;
import com.example.science_museum.ui.home.HomeViewModel;
import com.example.science_museum.ui.home.VisitByAppointmentViewModel;
import com.example.science_museum.ui.home.news.NewsEntriesAdapter;

public class AppointmentsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AppointmentsViewModel mViewModel;

    private void init()
    {
        mViewModel= new AppointmentsViewModel(this);
        mRecyclerView=findViewById(R.id.recyclerView);
        if(mViewModel.getAllAppointments()==null)return;
        mRecyclerView.setAdapter(new AppointmentEntriesAdapter(mViewModel.getAllAppointments(),this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
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
        init();
    }
    private final static int intentLoginResult=1;

    private void checkIsLogin()
    {
        if(!mViewModel.isOnline())
        {
            Intent intentLogin=new Intent(this, LoginActivity.class);
            startActivityForResult(intentLogin,intentLoginResult);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=intentLoginResult)
        {
            onBackPressed();
        }

    }
}
