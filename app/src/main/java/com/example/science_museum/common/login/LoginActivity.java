package com.example.science_museum.common.login;

import androidx.appcompat.app.AppCompatActivity;
import com.example.science_museum.R;
import com.example.science_museum.ui.home.VisitByAppointmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected EditText mUID,mPassword;
    private UserViewModel mUserViewModel;
    protected Button mLogin;

    private void initButtons()
    {
        mUID=findViewById(R.id.editViewUID);
        mPassword=findViewById(R.id.editViewPassword);
        mLogin=findViewById(R.id.buttonLogin);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserViewModel.login(Long.parseLong(mUID.getText().toString()),mPassword.getText().toString());
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mUserViewModel=new UserViewModel(this);
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
