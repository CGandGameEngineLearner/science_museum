package com.example.science_museum.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.science_museum.R;
import com.example.science_museum.common.appointment.AppointmentsActivity;
import com.example.science_museum.common.data.dao.Appointment;
import com.example.science_museum.common.user.LoginActivity;
import com.example.science_museum.common.user.ModifyUserActivity;
import com.example.science_museum.common.user.SignUpActivity;

public class MineFragment extends Fragment {

    private MineViewModel mViewModel;
    public View mView;
    private Button loginButton,signUpButton,modifyButton,appointmentRecordButton;
    private TextView textViewUsername;
    private static final int intentLoginSuccessResult=1,intentModifySuccessResult=2;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    private void init()
    {
        textViewUsername=mView.findViewById(R.id.editTextUserName);
        loginButton=mView.findViewById(R.id.buttonLogin);
        signUpButton=mView.findViewById(R.id.buttonSignUp);
        textViewUsername=mView.findViewById(R.id.editTextUsername);
        modifyButton=mView.findViewById(R.id.buttonModify);
        appointmentRecordButton=mView.findViewById(R.id.appointmentRecordButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin=new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intentLogin,intentLoginSuccessResult);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp=new Intent(getActivity(), SignUpActivity.class);
                startActivity(intentSignUp);
            }
        });
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentModify=new Intent(getActivity(), ModifyUserActivity.class);
                startActivityForResult(intentModify,intentModifySuccessResult);
            }
        });
        appointmentRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAppointmentRecord=new Intent(getActivity(), AppointmentsActivity.class);
                startActivity(intentAppointmentRecord);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_mine, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        // TODO: Use the ViewModel
        init();

        updateUserState();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==intentLoginSuccessResult)
        {
            updateUserState();
        }
        else if(requestCode==intentModifySuccessResult)
        {
            updateUserState();
        }
    }

    public void updateUserState()
    {
        Log.d("MineFragment","checkUserState()");
        if(mViewModel.isOnline())
        {
            textViewUsername.setText(mViewModel.getUsername());
        }
        else
        {
            textViewUsername.setText("未登录");
        }
    }

}
