package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView count ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        count = (TextView) findViewById(R.id.valueCount);
        Intent intent = getIntent();
        count.setText(intent.getCharSequenceExtra("count"));
    }
}