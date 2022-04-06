package com.example.hellotoastchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button btnToast, btnClickCount;
    Context context;
    int currentCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        tv = (TextView) findViewById(R.id.textCount);
        btnToast = (Button) findViewById(R.id.btnClickToast);
        btnClickCount = (Button) findViewById(R.id.btnClickCount);
        tv.setText(String.valueOf(currentCounter));
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Hello world", Toast.LENGTH_SHORT).show();
            }
        });

        btnClickCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCounter = Integer.parseInt(tv.getText().toString());
                tv.setText(String.valueOf(currentCounter+1));
                Toast.makeText(context, "Count", Toast.LENGTH_SHORT).show();
            }
        });
    }


}