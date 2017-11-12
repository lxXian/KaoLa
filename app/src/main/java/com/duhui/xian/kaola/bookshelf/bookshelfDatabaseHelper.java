package com.duhui.xian.kaola.bookshelf;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by min on 2017/11/8.
 */

public class bookshelfDatabaseHelper extends SQLiteOpenHelper {

    //primary key 将id列设为主键    autoincrement表示id列是自增长的
    public static final String CREATE_BOOKlIST="create table BookList("+
            "id integer primary key autoincrement,"+
            "name text,"+
            "author text,"+
            "desc text";
    private Context mContext;

    //构造方法：第一个参数Context，第二个参数数据库名，第三个参数cursor允许我们在查询数据的时候返回一个自定义的光标位置，一般传入的都是null，第四个参数表示目前库的版本号（用于对库进行升级）
    public  bookshelfDatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory , int version){
        super(context,name ,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //调用SQLiteDatabase中的execSQL（）执行建表语句。
        db.execSQL(CREATE_BOOKlIST);
        //创建成功
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
