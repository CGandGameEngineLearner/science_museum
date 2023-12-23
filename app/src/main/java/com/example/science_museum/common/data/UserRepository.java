package com.example.science_museum.common.data;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class UserRepository {

    private Context mContext;

    public UserRepository(@NonNull Context context)
    {
        mContext=context;
    }

    private UserDataBaseHelper mUserDataBaseHelper;
    public class UserDataBaseHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME="prefabricate_data.db";
        private static final int DATABASE_VERSION=3;
        private static final String TABLE_NAME="users";
        private static final String KEY_UID="uid";
        private static final String KEY_USERNAME="user_name",KEY_GENDER="gender",KEY_INTERESTS="interests";
        private static final String KEY_TELEPHONE_NUMBER="telephone_number";
        private static final String KEY_PASSWORD="password";

        private static final String queryLogin="select * from "+TABLE_NAME+" where "+
                KEY_USERNAME+" = ?  and "+KEY_PASSWORD+" = ?";

        private static final String insertSignUp="insert into "+TABLE_NAME+" ("+KEY_UID+", "+KEY_USERNAME+", "+KEY_PASSWORD+
                ", "+KEY_GENDER+", "+KEY_TELEPHONE_NUMBER+", "+KEY_INTERESTS+") values (?,?,?,?,?)";



        public UserDataBaseHelper(@NonNull Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
        protected boolean login(long uid, String password) {
            SQLiteDatabase db = mUserDataBaseHelper.getReadableDatabase();

            // 获取密码哈希
            String passwordHash = hashPassword(password);

            // 查询数据库
            Cursor cursor = db.rawQuery(queryLogin, new String[]{Long.toString(uid), password});

            boolean loginSuccess = cursor.getCount() > 0;
            cursor.close();

            return loginSuccess;
        }

        protected boolean signUp(UserBean userBean) {
            userBean.password = hashPassword(userBean.password);//对密码进行加密
            SQLiteDatabase db = mUserDataBaseHelper.getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_USERNAME, userBean.username);
            values.put(KEY_PASSWORD, userBean.password); // Ensure password is hashed
            values.put(KEY_TELEPHONE_NUMBER, userBean.telephone_number);
            values.put(KEY_GENDER, userBean.gender);
            values.put(KEY_INTERESTS,userBean.interests);
            long result;
            try {
                // Inserting Row
                result=db.insert(TABLE_NAME, null, values);
                db.close(); // Closing database connection
            }
            catch (SQLiteException e)
            {
                Log.e("UserRepository SQLiteException","insert new user");
                return false;
            }
            if(result<0)return false;
            return true;
        }

        // 哈希函数，用于哈希密码
        private String hashPassword(String password) {
            // 这里你可以使用更安全的哈希函数，比如SHA-256
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                return bytesToHex(hash);
            } catch (NoSuchAlgorithmException e) {
                // 在实际应用中，你应该更合适地处理这个异常
                throw new RuntimeException("Failed to hash password", e);
            }
        }

        private String bytesToHex(byte[] hash) {
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
    }



    // 登录功能
    public boolean login(long uid,String password) {
        return mUserDataBaseHelper.login(uid,password);
    }

    public boolean signUp(UserBean userBean)
    {
       return mUserDataBaseHelper.signUp(userBean);
    }

}
