package com.example.science_museum.common.data;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class UserRepository {

    private Context mContext;

    UserRepository(@NonNull Context context)
    {
        mContext=context;
    }

    private UserDataBaseHelper mUserDataBaseHelper;
    public class UserDataBaseHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME="prefabricate_data.db";
        private static final int DATABASE_VERSION=3;

        public UserDataBaseHelper(@NonNull Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
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

    // 登录功能
    public boolean login(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // 获取密码哈希
        String passwordHash = hashPassword(password);

        // 查询数据库
        Cursor cursor = db.query("users", new String[]{"id"}, "username=? AND password_hash=?",
                new String[]{username, passwordHash}, null, null, null);

        boolean loginSuccess = cursor.getCount() > 0;
        cursor.close();

        return loginSuccess;
    }


}
