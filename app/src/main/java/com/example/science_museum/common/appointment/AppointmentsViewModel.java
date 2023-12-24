package com.example.science_museum.common.appointment;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.science_museum.common.data.AppointmentBean;
import com.example.science_museum.common.data.AppointmentRepository;
import com.example.science_museum.common.data.UserRepository;
import com.example.science_museum.common.data.UserStateRepository;

import java.util.List;

public class AppointmentsViewModel extends ViewModel {
    private AppointmentRepository mAppointmentRepository;
    private Context mContext;
    private UserStateRepository mUserStateRepository=UserStateRepository.getInstance();
    public  AppointmentsViewModel(Context context)
    {
        mContext=context;
        mAppointmentRepository=new AppointmentRepository(context);
    }

    public List<AppointmentBean> getAllAppointments()
    {
        return mAppointmentRepository.getAppointmentsByUID(mUserStateRepository.getUID());
    }
    public boolean isOnline()
    {
        return mUserStateRepository.isOnline();
    }

}
