package android.k17bn.studentmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class StudentDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "REG";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PHONE";
    public static final String COL_5 = "STREAM";


    public StudentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,REG TEXT, NAME TEXT,EMAIL TEXT,PHONE TEXT,STREAM TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String reg, String name, String email, String phone, String stream)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL_1,reg);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,phone);
        contentValues.put(COL_5,stream);
        long res=db.insert(TABLE_NAME,null,contentValues);
        if(res==-1)
            return false;
        else
            return true;

    }

    public boolean checkUser(String reg){
        String[] columns={COL_0};
        SQLiteDatabase db=getReadableDatabase();
        String selection = COL_1 + "=?";
        String[] selectionArgs={reg};
        Cursor cursor=db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count>0)
            return true;
        else
            return false;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String reg,String name, String email,String phone,String stream)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL_1,reg);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,phone);
        contentValues.put(COL_5,stream);
        db.update(TABLE_NAME,contentValues,"REG = ?",new String[]{reg});
        return true;

    }
    public Integer deleteData(String reg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"REG = ?",new String[]{reg});
    }

}
