package com.example.science_museum.common.signup;

import androidx.appcompat.app.AppCompatActivity;
import com.example.science_museum.R;
import com.example.science_museum.common.data.UserBean;
import com.example.science_museum.common.login.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextUserName,editTextPassword,editTextGender,
            editTextTelephoneNumber,editTextIntersts;
    private Button buttonSignUp;
    private void init()
    {
        editTextUserName=findViewById(R.id.editTextUserName);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextGender=findViewById(R.id.editTextGender);
        editTextTelephoneNumber=findViewById(R.id.editTextTelephoneNumber);

        buttonSignUp=findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean userBean=new UserBean();
                userBean.username=editTextUserName.getText().toString();
                userBean.password=editTextPassword.getText().toString();
                userBean.interests=editTextIntersts.getText().toString();
                userBean.telephone_number=editTextTelephoneNumber.getText().toString();

            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


}
