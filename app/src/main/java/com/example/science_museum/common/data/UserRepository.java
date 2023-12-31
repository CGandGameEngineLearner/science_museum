package com.example.science_museum.common.data;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static androidx.room.util.CursorUtil.getColumnIndex;

public class UserRepository {

    private Context mContext;

    public UserRepository(@NonNull Context context)
    {
        mContext=context;
        mUserDataBaseHelper=new UserDataBaseHelper(context);
    }

    private UserDataBaseHelper mUserDataBaseHelper;
    public class UserDataBaseHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME="prefabricate_data.db";
        private static final int DATABASE_VERSION=3;
        private static final String TABLE_NAME="users";
        private static final String KEY_UID="uid";
        private static final String KEY_USERNAME="username",KEY_GENDER="gender",KEY_INTERESTS="interests";
        private static final String KEY_TELEPHONE_NUMBER="telephone_number";
        private static final String KEY_PASSWORD="password";

        private static final String QUERYLOGIN="select * from "+TABLE_NAME+" where "+
                KEY_UID+" = ?  and "+KEY_PASSWORD+" = ?";

        private static final String QUERYUSERBEAN="select * from "+TABLE_NAME+" where "+
                KEY_UID+" = ?";

        private static final String INSERTSIGNUP="insert into "+TABLE_NAME+" ("+KEY_USERNAME+", "+KEY_PASSWORD+
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

        protected UserBean getUserBeanByUID(long uid)
        {
            SQLiteDatabase db = mUserDataBaseHelper.getReadableDatabase();
            UserBean result=new UserBean();
            try {
                Cursor cursor = db.rawQuery(QUERYUSERBEAN, new String[]{Long.toString(uid)});
                if (cursor != null && cursor.moveToFirst()) {
                    result.uid = cursor.getLong(getColumnIndex(cursor, "uid"));
                    result.username = cursor.getString(getColumnIndex(cursor, "username"));
                    result.telephone_number = cursor.getString(getColumnIndex(cursor, "telephone_number"));
                    result.gender = cursor.getString(getColumnIndex(cursor, "gender"));
                    result.interests = cursor.getString(getColumnIndex(cursor, "interests"));
                    Log.d("UserRepository","query successed");
                }
                else
                {
                    Log.d("UserRepository","query failded");
                    return new UserBean();
                }
            }
            catch (SQLiteException e)
            {
                Log.e("UserRepository SQLiteException","query UserBeanByUID");
                return new UserBean();
            }
            catch (CursorIndexOutOfBoundsException e)
            {
                Log.e("UserRepository CursorIndexOutOfBoundsException","query UserBeanByUID");
                return new UserBean();
            }
            return result;
        }

        protected boolean login(long uid, String password) {
            SQLiteDatabase db = mUserDataBaseHelper.getReadableDatabase();
            Cursor cursor;
//            Cursor cursor = db.query("users", null, null, null, null, null, null);
//            long cuid=-1;
//            if (cursor.moveToFirst()) {
//                do {
//                    cuid=cursor.getLong(cursor.getColumnIndex("uid"));
//                    //Log.d("db",String.format("uid=%d",cuid));
//                    // 获取每个用户的数据，例如：
//                    String username = cursor.getString(cursor.getColumnIndex("username"));
//                    String spassword = cursor.getString(cursor.getColumnIndex("password"));
//                    //Log.d("db",String.format("username=%s",username));
//                    //Log.d("db",String.format("password=%s",spassword));
//                    // 获取其他字段数据
//                } while (cursor.moveToNext());
//            }



            // 获取密码哈希
            String passwordHash = hashPassword(password);
            //Log.d("db signUp",String.format("password=%s",passwordHash));

            // 查询数据库
            cursor = db.rawQuery(QUERYLOGIN, new String[]{Long.toString(uid), passwordHash});
            //String username = cursor.getString(cursor.getColumnIndex("username"));

            boolean loginSuccess = cursor.getCount() > 0;
            Log.d("Login",String.format("Verify success?%b",loginSuccess));
            cursor.close();

            return loginSuccess;
        }

        protected long signUp(UserBean userBean) {
            userBean.password = hashPassword(userBean.password);//对密码进行加密
            Log.d("db signUp",String.format("password=%s",userBean.password));
            SQLiteDatabase db = mUserDataBaseHelper.getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_USERNAME, userBean.username);
            values.put(KEY_PASSWORD, userBean.password); // Ensure password is hashed
            values.put(KEY_TELEPHONE_NUMBER, userBean.telephone_number);
            values.put(KEY_GENDER, userBean.gender);
            values.put(KEY_INTERESTS,userBean.interests);
            long uid;
            try {
                // Inserting Row
                uid=db.insert(TABLE_NAME, null, values);
                db.close(); // Closing database connection,
                Log.d("SignUp",String.format("uid=%d",uid));
            }
            catch (SQLiteException e)
            {
                Log.e("UserRepository SQLiteException","insert new user");
                return -1;
            }
            if(uid<0)return -1;

            return uid;
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
        protected boolean modifyUser(UserBean userBean) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_USERNAME, userBean.username);
            values.put(KEY_PASSWORD, hashPassword(userBean.password)); // 对密码进行哈希处理
            values.put(KEY_TELEPHONE_NUMBER, userBean.telephone_number);
            values.put(KEY_GENDER, userBean.gender);
            values.put(KEY_INTERESTS, userBean.interests);

            try {
                // 更新数据库中的记录，假设 uid 用作匹配条件
                int rowsAffected = db.update(TABLE_NAME, values, KEY_UID + " = ?", new String[] { Long.toString(userBean.uid) });
                db.close(); // 关闭数据库连接
                return rowsAffected > 0; // 如果更新的行数大于0，则表示更新成功
            } catch (SQLiteException e) {
                Log.e("UserRepository SQLiteException", "modify user");
                return false; // 发生异常时返回 false
            }
        }
    }



    // 登录功能
    public boolean login(long uid,String password) {
        return mUserDataBaseHelper.login(uid,password);
    }

    public long signUp(UserBean userBean)
    {
       return mUserDataBaseHelper.signUp(userBean);
    }

    public UserBean getUserBeanByUID(long uid)
    {
        return mUserDataBaseHelper.getUserBeanByUID(uid);
    }

    public boolean modifyUser(UserBean userBean)
    {
        return mUserDataBaseHelper.modifyUser(userBean);
    }

}
