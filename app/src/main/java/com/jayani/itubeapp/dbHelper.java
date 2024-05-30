package com.jayani.itubeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "Deakin_youtubeApp";
    //Database Table name
    private static final String TABLE_NAME = "myFav";
    private static final String TABLE_NAME2 = "User";
    //Table columns
    public static final String ID = "id";
    public static final String URL = "url";
    public static final String USERID = "userId";
    public static final String USERNAME = "username";
    public static final String USERPASSWORD = "userPassword";


    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT,"+URL+" TEXT NOT NULL);";


    //Constructor
    public dbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL("create table " + TABLE_NAME2 +"("+USERID+
                " INTEGER PRIMARY KEY AUTOINCREMENT,"+USERNAME+" TEXT NOT NULL,"+USERPASSWORD+" TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public void addItem(itemModel itemModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.URL, itemModel.getUrl());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(dbHelper.TABLE_NAME, null,contentValues);
    }

    public void userAdd(userModel userModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.USERNAME, userModel.getUserName());
        contentValues.put(dbHelper.USERPASSWORD, userModel.getUserPassword());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(dbHelper.TABLE_NAME2, null,contentValues);
    }

    public ArrayList<userModel> ValidLogin(String username, String Password){
        ArrayList<userModel> userList=new ArrayList<userModel>();
        try
        {
            Cursor cursor=sqLiteDatabase.rawQuery("select * from"+ TABLE_NAME2 +"where USERNAME='"+username+"'and USERPASSWORD='"+Password+"'",null);
            if(cursor.moveToFirst())
            {
                do{
                    userModel user=new userModel();
                    user.setUserName(cursor.getString(1));
                    user.setUserPassword(cursor.getString(2));
                    userList.add(user);

                }while (cursor.moveToNext());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  userList;
    }

    public ArrayList<itemModel> getItemList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        ArrayList<itemModel> storeTasks = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String url = cursor.getString(1);

                storeTasks.add(new itemModel(id, url));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeTasks;
    }


//    public void deleteTask(int id){
//        sqLiteDatabase = this.getWritableDatabase();
//        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
//                {String.valueOf(id)});
//
//        sqLiteDatabase.close();
//    }
}
