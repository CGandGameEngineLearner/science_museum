package com.example.science_museum.common.login;

import androidx.appcompat.app.AppCompatActivity;
import com.example.science_museum.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected EditText mName,mPassword;

    protected Button mLogin;

    private void initButtons()
    {
        mName=findViewById(R.id.editViewName);
        mPassword=findViewById(R.id.editViewPassword);
        mLogin=findViewById(R.id.buttonLogin);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
