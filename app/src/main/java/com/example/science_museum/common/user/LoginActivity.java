package com.example.science_museum.common.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.science_museum.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    protected EditText mUID,mPassword;
    private LoginViewModel mUserViewModel;
    protected Button mLogin;
    private boolean mOnline=false;

    private void initButtons()
    {
        mUID=findViewById(R.id.editViewUID);
        mPassword=findViewById(R.id.editViewPassword);
        mLogin=findViewById(R.id.buttonLogin);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnline=mUserViewModel.login(Long.parseLong(mUID.getText().toString()),mPassword.getText().toString());
                createLoginAlertDiaLog(mOnline);
                if(mOnline) {
                    // 假设登录成功 返回码就为1
                    Intent returnIntent = new Intent();
                    setResult(1, returnIntent);
                    finish();
                }
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
        mUserViewModel=new LoginViewModel(this);
        initButtons();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void createLoginAlertDiaLog(boolean online)
    {
        if(online)
        {
            // 登录成功的提示框 并显示uid
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("登录成功");
            Log.d("Login","successed");

            View NewsDetail=getLayoutInflater().inflate(R.layout.layout_login_alert_dialog,null);

            TextView vContent = NewsDetail.findViewById(R.id.textViewContent);
            TextView vTitle=NewsDetail.findViewById(R.id.textViewTitle);

            vContent.setText("");
            vTitle.setText("");

            builder.setView(NewsDetail);
            AlertDialog alertDialog =builder.create();//这个方法可以返回一个alertDialog对象
            alertDialog.show();
        }
        else
        {
            // 注册失败的提示框
            // 注册成功的提示框 并显示uid
            AlertDialog.Builder builder = new AlertDialog.Builder(this);


            builder.setTitle("登录失败");
            Log.d("Login","failed");

            View NewsDetail=getLayoutInflater().inflate(R.layout.layout_login_alert_dialog,null);

            TextView vContent = NewsDetail.findViewById(R.id.textViewContent);
            TextView vTitle=NewsDetail.findViewById(R.id.textViewTitle);

            vContent.setText("账号或密码错误");
            vTitle.setText("请检查你的登录信息");

            builder.setView(NewsDetail);
            AlertDialog alertDialog =builder.create();
            alertDialog.show();
        }

    }
}
