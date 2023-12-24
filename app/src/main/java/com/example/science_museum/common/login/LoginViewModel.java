package com.example.science_museum.common.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.science_museum.common.data.UserBean;
import com.example.science_museum.common.data.UserRepository;
import com.example.science_museum.common.data.UserStateRepository;

public class LoginViewModel extends ViewModel {
    private UserRepository mUserRepository;
    private Context mContext;
    LoginViewModel(@NonNull Context context)
    {
        mContext=context;
        mUserRepository=new UserRepository(context);
    }

    public boolean login(long uid,String password)
    {
        boolean online=mUserRepository.login(uid,password);
        UserStateRepository userStateRepository=UserStateRepository.getInstance();
        userStateRepository.setOnline(online);
        if(online)
        {
            userStateRepository.updateUserStateByUID(uid,mContext);
        }
        return online;
    }


}
