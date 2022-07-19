package com.phutt.managestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.phutt.managestudent.entity.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHandler databaseHandler;
    Context context;
    SQLiteDatabase db;

    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";

    private static final String KEY_MSSV = "mssv";
    private static final String KEY_NAME = "hoten";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NGAY_SINH = "ngaysinh";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
         listView = findViewById(R.id.list_view);

        // Open DB

        String path = getFilesDir() + "/mydb";
        try {
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            createTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // createTable();

        findViewById(R.id.button_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.beginTransaction();
                try {
//                    Faker faker = new Faker();
//                    String name = faker.name.name();
//                    String phone = faker.phoneNumber.phoneNumber();
//
//                    db.execSQL("insert into TABLE_NAME(name, phone) values('" + name + "', '" + phone + "')");

                    ContentValues cv = new ContentValues();
                    cv.put(KEY_MSSV, "1A");
                    cv.put(KEY_NAME, "ABC");
                    cv.put(KEY_EMAIL, "anv@gmail.com");
                    cv.put(KEY_NGAY_SINH, "05/08/2001");
                    long ret = db.insert(TABLE_NAME, null, cv);
                    Log.v("TAG", "ret = " + ret);

                    cv.put(KEY_MSSV, "2B");
                    cv.put(KEY_NAME, "ABC1");
                    cv.put(KEY_EMAIL, "anv1@gmail.com");
                    cv.put(KEY_NGAY_SINH, "05/08/2001");
                    ret = db.insert(TABLE_NAME, null, cv);
                    Log.v("TAG", "ret = " + ret);

                    cv.clear();

                    String[] columns = {KEY_MSSV, KEY_NAME, KEY_EMAIL, KEY_NGAY_SINH};
                    Cursor cs = db.query(TABLE_NAME, columns,
                            null, null, null, null ,null);

                    Log.v("TAG", "# records: " + cs.getCount());

                    cs.moveToPosition(-1);
                    while (cs.moveToNext()) {
                        String mssv = cs.getString(0);
                        String name = cs.getString(1);
                        String email = cs.getString(2);
                        String ngaysinh = cs.getString(3);

                        Log.v("TAG", mssv + " --- " +name + " --- " + name);
                    }

                    ItemAdapter adapter = new ItemAdapter(cs);
                    listView.setAdapter(adapter);
                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });

        findViewById(R.id.button_update).setVisibility(1);
        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.beginTransaction();
                try {
//                    db.execSQL("update TABLE_NAME set phone='555-0000' where name='AAA'");

                    ContentValues cv = new ContentValues();
                    cv.put(KEY_NAME, "Maria");

                    long ret = db.update(TABLE_NAME, cv, "recID > 9 and recID < 16", null);
                    Log.v("TAG", "ret = " + ret);

                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.beginTransaction();
                try {
//                    db.execSQL("delete from TABLE_NAME where recID<5");

                    long ret = db.delete(TABLE_NAME, "mssv = '1A' OR mssv = '2B' OR mssv = '3C'", null);
                    Log.v("TAG", "ret = " + ret);
                    String[] columns = {KEY_MSSV, KEY_NAME, KEY_EMAIL, KEY_NGAY_SINH};
                    Cursor cs = db.query(TABLE_NAME, columns,
                            null, null, null, null ,null);

                    Log.v("TAG", "# records: " + cs.getCount());

                    cs.moveToPosition(-1);
                    while (cs.moveToNext()) {
                        String mssv = cs.getString(0);
                        String name = cs.getString(1);
                        String email = cs.getString(2);
                        String ngaysinh = cs.getString(3);

                        Log.v("TAG", mssv + " --- " +name + " --- " + name);
                    }

                    ItemAdapter adapter = new ItemAdapter(cs);
                    listView.setAdapter(adapter);
                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });

        findViewById(R.id.button_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String sql = "select * from TABLE_NAME";
//                Cursor cs = db.rawQuery(sql, null);
                String[] columns = {KEY_MSSV, KEY_NAME, KEY_EMAIL, KEY_NGAY_SINH};
                Cursor cs = db.query(TABLE_NAME, columns,
                        null, null, null, null ,null);

                Log.v("TAG", "# records: " + cs.getCount());

                cs.moveToPosition(-1);
                while (cs.moveToNext()) {
                    String mssv = cs.getString(0);
                    String name = cs.getString(1);
                    String email = cs.getString(2);
                    String ngaysinh = cs.getString(3);

                    Log.v("TAG", mssv + " --- " +name + " --- " + name);
                }

                ItemAdapter adapter = new ItemAdapter(cs);
                listView.setAdapter(adapter);
            }
        });
    }

    public void createTable() {
        db.beginTransaction();
        try {
            db.execSQL("create table "+TABLE_NAME+"(" +
                    KEY_MSSV+ " text PRIMARY KEY," +
                    KEY_NAME + " text," +
                    KEY_EMAIL + " text," +
                    KEY_NGAY_SINH + " text)");


            db.execSQL("insert into "+TABLE_NAME+"(mssv, hoten, email, ngaysinh) values('11', 'a11', '1@gmail.com', '20/01/2001')");
            db.execSQL("insert into "+TABLE_NAME+"(mssv, hoten, email, ngaysinh) values('12', 'b12', '1@gmail.com', '20/01/2001')");
            db.execSQL("insert into "+TABLE_NAME+"(mssv, hoten, email, ngaysinh) values('13', 'c13', '1@gmail.com', '20/01/2001')");

            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}