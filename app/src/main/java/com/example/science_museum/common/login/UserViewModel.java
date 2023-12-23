package com.example.science_museum.common.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.science_museum.common.data.UserBean;
import com.example.science_museum.common.data.UserRepository;

public class UserViewModel extends ViewModel {
    private UserRepository mUserRepository;
    UserViewModel(@NonNull Context context)
    {
        mUserRepository=new UserRepository(context);
    }

    public boolean login(long uid,String password)
    {
        return mUserRepository.login(uid,password);
    }

    public boolean signUp(UserBean userBean)
    {
        return mUserRepository.signUp(userBean);
    }
}
