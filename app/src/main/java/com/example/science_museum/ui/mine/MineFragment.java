package com.example.science_museum.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.science_museum.R;
import com.example.science_museum.common.login.LoginActivity;
import com.example.science_museum.common.signup.SignUpActivity;
import com.example.science_museum.ui.home.news.NewsListActivity;

public class MineFragment extends Fragment {

    private MineViewModel mViewModel;
    public View mView;
    private Button loginButton,signUpButton;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    private void init()
    {
        loginButton=mView.findViewById(R.id.buttonLogin);
        signUpButton=mView.findViewById(R.id.buttonSignUp);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin=new Intent(getActivity(), LoginActivity.class);
                startActivity(intentLogin);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp=new Intent(getActivity(), SignUpActivity.class);
                startActivity(intentSignUp);
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
    }

}
