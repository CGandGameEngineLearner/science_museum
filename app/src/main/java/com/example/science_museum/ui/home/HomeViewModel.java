package com.example.science_museum.ui.home;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeViewModel extends AndroidViewModel {


    public HomeViewModel(@NonNull Application application) {
        super(application);
        mNewsDataBaseHelper=new NewsDataBaseHelper(getApplication());
    }

    // TODO: Implement the ViewModel
    public class News {
        public int id;
        public String title;
        public String date;
        public String summary;
    }

    private NewsDataBaseHelper mNewsDataBaseHelper;

    public class NewsDataBaseHelper extends SQLiteOpenHelper {

        // Database version and name
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "newsDatabase";

        // News table name and column names
        private static final String TABLE_NEWS = "news";
        private static final String KEY_ID = "id";
        private static final String KEY_TITLE = "title";
        private static final String KEY_DATE = "date";
        private static final String KEY_SUMMARY = "summary";

        public NewsDataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_NEWS_TABLE = "CREATE TABLE " + TABLE_NEWS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                    + KEY_DATE + " TEXT," + KEY_SUMMARY + " TEXT" + ")";
            db.execSQL(CREATE_NEWS_TABLE);
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
            values.put(KEY_DATE, news.date);
            values.put(KEY_SUMMARY, news.summary);

            db.insert(TABLE_NEWS, null, values);
            db.close();
        }

        // Method to get all news items
        public List<News> getAllNews() {
            List<News> newsList = new ArrayList<>();
            String selectQuery = "SELECT  * FROM " + TABLE_NEWS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    News news = new News();
                    news.id=cursor.getInt(cursor.getColumnIndex(KEY_ID));
                    news.title=cursor.getString(cursor.getColumnIndex(KEY_TITLE));
                    news.date=cursor.getString(cursor.getColumnIndex(KEY_DATE));
                    news.summary=cursor.getString(cursor.getColumnIndex(KEY_SUMMARY));
                    newsList.add(news);
                } while (cursor.moveToNext());
            }

            cursor.close();
            return newsList;
        }
    }

    public void getAllNews()
    {
        mNewsDataBaseHelper.getAllNews();
    }
}
