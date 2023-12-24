package com.example.science_museum.common.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.science_museum.R;
import com.example.science_museum.common.data.UserBean;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModifyUserActivity extends AppCompatActivity {
    private final static int intentLoginResult=1;
    private EditText editTextUserName,editTextPassword,editTextGender,
            editTextTelephoneNumber,editTextIntersts;
    private Button buttonModify;
    private ModifyUserViewModel mModifyUserViewModel;
    private void init()
    {
        editTextUserName=findViewById(R.id.editTextUserName);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextGender=findViewById(R.id.editTextGender);
        editTextTelephoneNumber=findViewById(R.id.editTextTelephoneNumber);
        editTextIntersts=findViewById(R.id.editTextIntersts);
        buttonModify=findViewById(R.id.buttonModify);


        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean userBean=new UserBean();

                userBean.username=editTextUserName.getText().toString();
                userBean.password=editTextPassword.getText().toString();
                userBean.interests=editTextIntersts.getText().toString();
                userBean.telephone_number=editTextTelephoneNumber.getText().toString();
                boolean successe=mModifyUserViewModel.modifyUser(userBean);
                if(successe) {
                    // 假设登录成功 返回码就为1
                    Intent returnIntent = new Intent();
                    setResult(1, returnIntent);
                    finish();
                }
                createModifyAlertDiaLog(successe);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mModifyUserViewModel=new ModifyUserViewModel(this);
        init();
        checkIsLogin();
    }

    private void checkIsLogin()
    {
        if(!mModifyUserViewModel.isOnline())
        {
            Intent intentLogin=new Intent(this, LoginActivity.class);
            startActivityForResult(intentLogin,intentLoginResult);
        }
        else
        {
            UserBean userBean=mModifyUserViewModel.getUserBean();
            editTextUserName.setText(userBean.username);
            editTextGender.setText(userBean.gender);
            editTextIntersts.setText(userBean.interests);
            editTextTelephoneNumber.setText(userBean.telephone_number);
        }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=intentLoginResult)
        {
            onBackPressed();
        }

    }
    private void createModifyAlertDiaLog(boolean success)
    {
        if(success)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("修改成功");
            Log.d("Modify","successed");

            View NewsDetail=getLayoutInflater().inflate(R.layout.layout_login_alert_dialog,null);

            TextView vContent = NewsDetail.findViewById(R.id.textViewContent);
            TextView vTitle=NewsDetail.findViewById(R.id.textViewTitle);

            vContent.setText("用户信息修改成功");
            vTitle.setText("提示");

            builder.setView(NewsDetail);
            AlertDialog alertDialog =builder.create();//这个方法可以返回一个alertDialog对象
            alertDialog.show();
        }
        else
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);


            builder.setTitle("修改失败");
            Log.d("Modify","failed");

            View NewsDetail=getLayoutInflater().inflate(R.layout.layout_login_alert_dialog,null);

            TextView vContent = NewsDetail.findViewById(R.id.textViewContent);
            TextView vTitle=NewsDetail.findViewById(R.id.textViewTitle);

            vContent.setText("请检查你填写的信息");
            vTitle.setText("修改失败");

            builder.setView(NewsDetail);
            AlertDialog alertDialog =builder.create();//这个方法可以返回一个alertDialog对象
            alertDialog.show();
        }

    }
}
