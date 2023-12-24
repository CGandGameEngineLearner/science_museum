package com.example.science_museum.ui.home;

import androidx.lifecycle.ViewModel;

import com.example.science_museum.common.data.UserStateRepository;

public class VisitByAppointmentViewModel extends ViewModel {
    private UserStateRepository mUserStateRepository=UserStateRepository.getInstance();
    public VisitByAppointmentViewModel()
    {

    }
    public boolean isOnline()
    {
        return mUserStateRepository.isOnline();
    }

}
