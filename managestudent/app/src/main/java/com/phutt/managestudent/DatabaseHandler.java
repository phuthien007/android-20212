package com.phutt.managestudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.phutt.managestudent.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";

    private static final String KEY_MSSV = "mssv";
    private static final String KEY_NAME = "hoten";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NGAY_SINH = "ngaysinh";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("DB", "START CREATE DB");
        String create_students_table = String.format("CREATE TABLE %s(%s VARCHAR PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_MSSV, KEY_NAME, KEY_EMAIL, KEY_NGAY_SINH);
        sqLiteDatabase.execSQL(create_students_table);
        String insertStudent = String.format("INSERT INTO %s VALUES " +
                "(%s , %s , %s , %s)," +
                "(%s , %s , %s , %s)," +
                "(%s , %s , %s , %s)," +
                "(%s , %s , %s , %s), ",
                TABLE_NAME,
                "1", "Nguyen Van A", "anv@gmail.com", "01/01/2001",
                "2", "Nguyen Van B", "bnv@gmail.com", "11/01/2001",
                "3", "Nguyen Van C", "cnv@gmail.com", "10/01/2001",
                "4", "Nguyen Van D", "dnv@gmail.com", "10/11/2001");
        sqLiteDatabase.execSQL(insertStudent);

        Log.i("DB", "END CREATE DB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(drop_students_table);

        onCreate(sqLiteDatabase);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MSSV, student.getMssv());
        values.put(KEY_NAME, student.getHoten());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_NGAY_SINH, student.getNgaysinh());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Student getStudent(String studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_MSSV + " = ?", new String[] { String.valueOf(studentId) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Student student = new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return student;
    }


    public List<Student> getAllStudents() {
        List<Student>  studentList = new ArrayList<>();
        String query = "SELECT * FROM "+ TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Student student = new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            studentList.add(student);
            cursor.moveToNext();
        }
        return studentList;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MSSV, student.getMssv());
        values.put(KEY_NAME, student.getHoten());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_NGAY_SINH, student.getNgaysinh());

        db.update(TABLE_NAME, values, KEY_MSSV + " = ?", new String[] { String.valueOf(student.getMssv()) });
        db.close();
    }

    public void deleteStudent(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_MSSV + " = ?", new String[] { String.valueOf(studentId) });
        db.close();
    }

}
