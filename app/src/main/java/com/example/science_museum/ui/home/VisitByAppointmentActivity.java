package com.example.science_museum.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import com.example.science_museum.R;
import com.example.science_museum.common.login.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class VisitByAppointmentActivity extends AppCompatActivity {
    private final static int intentLoginResult=1;
    VisitByAppointmentViewModel mVolunteerRecruitmentViewModel=new VisitByAppointmentViewModel();

    private void checkIsLogin()
    {
        if(!mVolunteerRecruitmentViewModel.isOnline())
        {
            Intent intentLogin=new Intent(this, LoginActivity.class);
            startActivityForResult(intentLogin,intentLoginResult);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_by_appointment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        checkIsLogin();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
