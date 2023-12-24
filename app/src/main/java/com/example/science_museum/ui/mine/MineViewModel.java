package com.example.science_museum.ui.mine;

import androidx.lifecycle.ViewModel;

import com.example.science_museum.common.data.UserStateRepository;

public class MineViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    UserStateRepository mUserState=UserStateRepository.getInstance();
    public boolean isOnline()
    {
        return mUserState.isOnline();
    }
    public  String getUsername()
    {
        return  mUserState.getUsername();
    }
}
