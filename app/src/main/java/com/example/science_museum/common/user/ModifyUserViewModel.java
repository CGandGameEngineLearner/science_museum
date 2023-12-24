package com.example.science_museum.common.user;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.science_museum.common.data.UserBean;
import com.example.science_museum.common.data.UserRepository;
import com.example.science_museum.common.data.UserStateRepository;

public class ModifyUserViewModel extends ViewModel {
    private Context mContext;
    private UserRepository mUserRepository;
    private UserStateRepository mUserStateRepository=UserStateRepository.getInstance();

    public ModifyUserViewModel(@NonNull Context context) {
        mContext=context;
        mUserRepository=new UserRepository(context);
    }

    public boolean modifyUser(UserBean userBean)
    {
        userBean.uid=mUserStateRepository.getUID();
        return mUserRepository.modifyUser(userBean);
    }
    public boolean isOnline()
    {
        return mUserStateRepository.isOnline();
    }
}
