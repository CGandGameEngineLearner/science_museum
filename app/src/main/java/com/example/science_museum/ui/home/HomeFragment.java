package com.example.science_museum.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.science_museum.ui.home.news.NewsEntriesAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;

import com.example.science_museum.R;
import com.example.science_museum.ui.home.news.NewsListActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private Button mVisitByAppointmentButton,mVolunteerRecruitmentButton,mAboutMuseumButton;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private Button mMoreNewsButton;
    private ViewPager mSlidePagerView;
    private View mView;
    private int[] mSlideIds;
    private HandlerThread backgroundThread;
    private Handler backgroundHandler;
    private ArrayList<ImageView> mSlideImages=new ArrayList<>();
    private RecyclerView mNewsRecyclerView;

    class SlidesViewAdapter extends PagerAdapter{

        private ArrayList<ImageView> imageViewList;

        public SlidesViewAdapter(ArrayList<ImageView> mImgList){
            imageViewList = mImgList;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            int newPosition = position % imageViewList.size();
            ImageView img = imageViewList.get(newPosition);

            container.addView(img);

            return img;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return imageViewList.size();
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o ;
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_home, container, false);
        initSlides();
        initButtons();
        return mView;
    }

    private void initButtons()
    {
        mMoreNewsButton=mView.findViewById(R.id.moreNewsButton);
        mVisitByAppointmentButton=mView.findViewById(R.id.visitByAppointmentButton);
        mVolunteerRecruitmentButton=mView.findViewById(R.id.volunteerRecruitmentButton);
        mAboutMuseumButton=mView.findViewById(R.id.aboutMuseumButton);
        mVisitByAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentVisitByAppointment=new Intent(getActivity(), VisitByAppointmentActivity.class);
                startActivity(intentVisitByAppointment);
            }
        });
        mVolunteerRecruitmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentVolunteerRecruitment=new Intent(getActivity(), VolunteerRecruitmentActivity.class);
                startActivity(intentVolunteerRecruitment);
            }
        });
        mAboutMuseumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentAboutMuseum=new Intent(getActivity(),AboutMuseumActivity.class);
                startActivity(intentAboutMuseum);
            }
        });
    }

    private void  initNews()
    {
        mNewsRecyclerView=mView.findViewById(R.id.news_recycler_view);
        mNewsRecyclerView.setAdapter(new NewsEntriesAdapter(mViewModel.getAllNews().subList(0,3),getActivity()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mNewsRecyclerView.setLayoutManager(layoutManager);
        mMoreNewsButton=mView.findViewById(R.id.moreNewsButton);
        mMoreNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNews=new Intent(getActivity(), NewsListActivity.class);
                startActivity(intentNews);
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel

        initNews();
    }

    private void initSlides()
    {
        mSlidePagerView=mView.findViewById(R.id.slideshow);
        mSlideIds=new int[]{
            R.mipmap.slide_1,
            R.mipmap.slide_2,
            R.mipmap.slide_3
        };
        ImageView imageView;
        for(int i=0;i<mSlideIds.length;i++)
        {
            imageView=new ImageView(getContext());
            imageView.setImageResource(mSlideIds[i]);
            mSlideImages.add(imageView);
        }

        //设置适配器
        mSlidePagerView.setAdapter(new SlidesViewAdapter(mSlideImages));
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
        int m = (Integer.MAX_VALUE / 2) %mSlideImages.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        mSlidePagerView.setCurrentItem(currentPosition);



        backgroundThread = new HandlerThread("BackgroundThread");
        backgroundThread.start();
        backgroundHandler = new Handler(backgroundThread.getLooper());


        backgroundHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            mSlidePagerView.setCurrentItem((mSlidePagerView.getCurrentItem()+1)%mSlideIds.length);
                        }
                    });
                }


                backgroundHandler.postDelayed(this, 5000);

            }
        }, 5000);
        Log.d("Slideshow","start play");
    }
}
