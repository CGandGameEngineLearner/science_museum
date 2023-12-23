package com.example.science_museum.ui.home;

import android.app.Application;
import com.example.science_museum.common.data.NewsRepository;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeViewModel extends AndroidViewModel {
    private NewsRepository mNewsRepository=new NewsRepository(getApplication());

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }




    public List<NewsRepository.News> getAllNews()
    {
        List<NewsRepository.News> result=mNewsRepository.getAllNews();
        Log.d("NewsData",String.format("news entries size=%d",result.size()));
        return result;
    }
}
