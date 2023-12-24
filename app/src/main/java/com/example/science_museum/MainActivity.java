package com.example.science_museum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 尝试复制数据库
        try {
            copyDatabaseFromResources();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intentMain = new Intent(MainActivity.this,ScienceMuseumActivity.class);
        startActivity(intentMain);
        Log.d("MainActivity","ScienceMuseumActivity");

    }
    private void copyDatabaseFromResources() throws IOException {
        // 检查数据库是否已存在
        File dbFile = getApplicationContext().getDatabasePath("prefabricate_data.db");
        if (dbFile.exists()) {
            Log.d("Database","数据库已存在");
            return;
        }

        // 确保目录存在
        dbFile.getParentFile().mkdirs();

        // 从 res/raw 中读取数据库文件
        InputStream is = getResources().openRawResource(R.raw.prefabricate_data);
        OutputStream os = new FileOutputStream(dbFile);

        // 将数据库文件复制到正确的位置
        byte[] buffer = new byte[1024];
        while (is.read(buffer) > 0) {
            os.write(buffer);
        }

        // 关闭流
        os.flush();
        os.close();
        is.close();
    }
}
