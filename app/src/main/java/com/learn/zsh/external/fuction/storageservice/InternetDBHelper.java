package com.learn.zsh.external.fuction.storageservice;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZhouShaohua on 2018/7/3.
 */
public class InternetDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "internet_info.db";
    private static final String INTERNET_STATUS_INFO_TABLE_NAME = "status";
    private static final String INTERNET_INFO_TABLE_NAME = "info";
    private static final String DROP_STATUS_TABLE = "drop table if exists " + INTERNET_STATUS_INFO_TABLE_NAME;
    private static final String DROP_INFO_TABLE = "drop table if exists " + INTERNET_INFO_TABLE_NAME;

    public InternetDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 支持的数据类型：integer 表示整型；real 表示浮点型； text 表示文本类型； blob 表示二进制类型；
     * eg1). create table book(id integer primary key autoincrement,
     * author text, price real, pages integer, name text);
     * <p>
     * eg2). create table Students(Stu_no integer key,Stu_name text NOT NULL,Stu_Sex text NOT NULL,
     * Stu_Age integer check(Stu_Age>6),Stu_class integer,Stu_mark real);
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Students(Stu_no integer primary key,Stu_name text NOT NULL,Stu_Sex text NOT NULL,\n" +
                "Stu_Age integer check(Stu_Age>6),Stu_class integer,Stu_mark real)");
        db.execSQL("create table book(id integer primary key autoincrement,\n" +
                "author text, price real, pages integer, name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_STATUS_TABLE);
        db.execSQL(DROP_INFO_TABLE);
        onCreate(db);
    }
}
