package com.example.science_museum.common.signup;

import androidx.appcompat.app.AppCompatActivity;
import com.example.science_museum.R;
import android.os.Bundle;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextUserName,editTextPassword,editTextGender,
            editTextTelephoneNumber,editTextIntersts;
    private void init()
    {
        editTextUserName=findViewById(R.id.editTextUserName);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextGender=findViewById(R.id.editTextGender);
        editTextTelephoneNumber=findViewById(R.id.editTextTelephoneNumber);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


}
