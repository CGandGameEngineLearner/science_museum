package com.example.science_museum.common.signup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.science_museum.common.data.UserBean;
import com.example.science_museum.common.data.UserRepository;

public class SignUpViewModel extends ViewModel {
    private Context mContext;
    private UserRepository mUserRepository;

    public SignUpViewModel(@NonNull Context context) {
        mContext=context;
        mUserRepository=new UserRepository(context);
    }

    public long signUp(UserBean userBean)
    {
        return mUserRepository.signUp(userBean);
    }
}
