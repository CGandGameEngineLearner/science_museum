package com.example.science_museum.ui.home;

import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.os.Handler;
import android.widget.ImageView;

import com.example.science_museum.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private ViewPager mSlidePagerView;
    private View mView;
    private int[] mSlideIds;
    private boolean mSlideshowRunning;
    private HandlerThread backgroundThread;
    private Handler backgroundHandler;
    private ArrayList<ImageView> mSlideImages=new ArrayList<>();;

    class SlidesViewAdapter extends PagerAdapter{

        private ArrayList<ImageView> imageViewList;

        public SlidesViewAdapter(ArrayList<ImageView> mImgList){
            imageViewList = mImgList;
        }

        // 1. 返回要显示的条目内容, 创建条目
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            // container: 容器: ViewPager
            // position: 当前要显示条目的位置 0 -> 4
            //newPosition = position % 5
            int newPosition = position % imageViewList.size();
            ImageView img = imageViewList.get(newPosition);
            // a. 把View对象添加到container中
            container.addView(img);
            // b. 把View对象返回给框架, 适配器
            return img;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return 3;   //返回一个无限大的值，可以 无限循环!!!!!
        }

        /**
         * 判断是否使用缓存, 如果返回的是true, 使用缓存. 不去调用instantiateItem方法创建一个新的对象
         */
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
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
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


        // 初始化后台线程和处理程序
        backgroundThread = new HandlerThread("BackgroundThread");
        backgroundThread.start();
        backgroundHandler = new Handler(backgroundThread.getLooper());

        // 在后台线程中执行轮询任务
        backgroundHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 更新 UI 的代码
                            mSlidePagerView.setCurrentItem((mSlidePagerView.getCurrentItem()+1)%mSlideIds.length);
                        }
                    });
                }

                // 继续下一次轮询
                backgroundHandler.postDelayed(this, 2000);

            }
        }, 2000);
        Log.d("Slideshow","start play");
    }
}
