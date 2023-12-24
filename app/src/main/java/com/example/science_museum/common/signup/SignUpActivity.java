package com.example.science_museum.common.signup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.science_museum.R;
import com.example.science_museum.common.data.UserBean;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextUserName,editTextPassword,editTextGender,
            editTextTelephoneNumber,editTextIntersts;
    private Button buttonSignUp;
    private SignUpViewModel mSignUpViewModel;
    private void init()
    {
        editTextUserName=findViewById(R.id.editTextUserName);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextGender=findViewById(R.id.editTextGender);
        editTextTelephoneNumber=findViewById(R.id.editTextTelephoneNumber);
        editTextIntersts=findViewById(R.id.editTextIntersts);
        buttonSignUp=findViewById(R.id.buttonSignUp);
        SignUpActivity signUpActivity=this;

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean userBean=new UserBean();

                userBean.username=editTextUserName.getText().toString();
                userBean.password=editTextPassword.getText().toString();
                userBean.interests=editTextIntersts.getText().toString();
                userBean.telephone_number=editTextTelephoneNumber.getText().toString();
                long uid=mSignUpViewModel.signUp(userBean);
                createSignUpAlertDiaLog(uid);
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mSignUpViewModel=new SignUpViewModel(this);
        init();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createSignUpAlertDiaLog(long uid)
    {
        if(uid>0)
        {
            // 注册成功的提示框 并显示uid
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("注册成功");
            Log.d("SignUp","successed");

            View NewsDetail=getLayoutInflater().inflate(R.layout.layout_login_alert_dialog,null);

            TextView vContent = NewsDetail.findViewById(R.id.textViewContent);
            TextView vTitle=NewsDetail.findViewById(R.id.textViewTitle);

            vContent.setText("UID:"+Long.toString(uid));
            vTitle.setText("请记住你的账号(UID),以后以此登录");

            builder.setView(NewsDetail);
            AlertDialog alertDialog =builder.create();//这个方法可以返回一个alertDialog对象
            alertDialog.show();
        }
        else
        {
            // 注册失败的提示框
            // 注册成功的提示框 并显示uid
            AlertDialog.Builder builder = new AlertDialog.Builder(this);


            builder.setTitle("注册失败");
            Log.d("SignUp","failed");

            View NewsDetail=getLayoutInflater().inflate(R.layout.layout_login_alert_dialog,null);

            TextView vContent = NewsDetail.findViewById(R.id.textViewContent);
            TextView vTitle=NewsDetail.findViewById(R.id.textViewTitle);

            vContent.setText("");
            vTitle.setText("请检查你你的注册信息");

            builder.setView(NewsDetail);
            AlertDialog alertDialog =builder.create();//这个方法可以返回一个alertDialog对象
            alertDialog.show();
        }

    }

}
