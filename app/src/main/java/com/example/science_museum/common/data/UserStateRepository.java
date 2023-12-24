package com.example.science_museum.common.data;

import android.content.Context;
import android.widget.Button;

public class UserStateRepository {
    static boolean mOnline=false;
    private Context mContext;
    private static UserBean mUserBean=new UserBean();
    private static UserRepository mUserRepository;
    private static UserStateRepository mInstance=new UserStateRepository();
    public UserStateRepository()
    {
    }
    public void updateUserStateByUID(long uid,Context context)
    {
        mUserRepository=new UserRepository(context);
        mUserBean=mUserRepository.getUserBeanByUID(uid);
    }
    public static long getUID()
    {
        return mUserBean.uid;
    }
    public static void setUID(long uid)
    {
        mUserBean.uid=uid;
    }
    public static String getUsername()
    {
        return mUserBean.username;
    }
    public static void setUsername(String username)
    {
        mUserBean.username=username;
    }
    public static UserStateRepository getInstance()
    {
        return mInstance;
    }
    public static boolean isOnline()
    {
        return mOnline;
    }
    public static void setOnline(boolean online)
    {
        mOnline=online;
    }
}
