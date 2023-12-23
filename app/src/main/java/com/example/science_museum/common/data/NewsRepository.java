package com.example.science_museum.common.data;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.science_museum.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsRepository {
    // TODO: Implement the ViewModel
    public class News {
        public int id;
        public String title;
        public String date;//（ISO8601 格式）的日期
        public String content;
        public String summary;
    }
    public NewsRepository(@NonNull Application application)
    {

        mNewsDataBaseHelper=new NewsDataBaseHelper(application);
    }

    public List<NewsRepository.News> getAllNews()
    {
        return mNewsDataBaseHelper.getAllNews();
    }

    private static NewsDataBaseHelper mNewsDataBaseHelper;

    public class NewsDataBaseHelper extends SQLiteOpenHelper {

        // Database version and name
        private static final int DATABASE_VERSION = 3;
        private static final String DATABASE_NAME = "prefabricate_data.db";

        // News table name and column names
        private static final String TABLE_NEWS = "news";
        private static final String KEY_ID = "id";
        private static final String KEY_TITLE = "title";
        private static final String KEY_CONTENT = "content";
        private static final String KEY_DATE = "date";
        private static final String KEY_SUMMARY = "summary";

        public NewsDataBaseHelper(@NonNull Application application) {
            super(application,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
//            String CREATE_NEWS_TABLE = "CREATE TABLE " + TABLE_NEWS + "("
//                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
//                    + KEY_DATE + " TEXT," + KEY_SUMMARY + " TEXT" + ")";
//            db.execSQL(CREATE_NEWS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        // Upgrade method omitted for brevity

        // Method to add a news item
        public void addNews(News news) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_TITLE, news.title);
            values.put(KEY_CONTENT, news.content);
            values.put(KEY_SUMMARY, news.summary);
            values.put(KEY_DATE,news.date);
            db.insert(TABLE_NEWS, null, values);
            db.close();
        }

        // Method to get all news items
        public List<NewsRepository.News> getAllNews() {
            List<NewsRepository.News> newsList = new ArrayList<NewsRepository.News>();


            SQLiteDatabase db = this.getWritableDatabase();
            List<String> tables = new ArrayList<>();
            Cursor cursor = null;
            try {
                cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        tables.add(cursor.getString(0));
                        cursor.moveToNext();
                    }
                }
            } catch (Exception e) {
                // 异常处理
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            Log.d("NewsData",String.format("tables.size()=%d tabls:",tables.size()));
            for(String s:tables)
            {
                Log.d("NewsData tabls:",s);
            }
            cursor.close();

            String selectQuery = "SELECT  * FROM " + TABLE_NEWS;
            cursor = db.rawQuery(selectQuery, null);
            Log.d("NewsData",String.format("cursor.getCount()=%d",cursor.getCount()));
            Log.d("NewsData",String.format("query news"));
            Log.d("NewsData",String.format("cursor.moveToFirst()=%b",cursor.moveToFirst()));
            if (cursor.moveToFirst()) {
                Log.d("NewsData",String.format("cursor.moveToFirst()=true"));
                while (!cursor.isAfterLast()){
                    News news = new News();
                    news.id=cursor.getInt(cursor.getColumnIndex(KEY_ID));
                    news.title=cursor.getString(cursor.getColumnIndex(KEY_TITLE));
                    news.content=cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
                    news.summary=cursor.getString(cursor.getColumnIndex(KEY_SUMMARY));
                    news.date=cursor.getString(cursor.getColumnIndex(KEY_DATE));
                    newsList.add(news);
                    Log.d("NewsData",String.format("news entries size=%d",newsList.size()));
                    cursor.moveToNext();
                }
            }

            cursor.close();
            return newsList;
        }
    }
}
