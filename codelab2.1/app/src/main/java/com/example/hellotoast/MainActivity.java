package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSay, btnCount;
    TextView count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSay = (Button) findViewById(R.id.say_hello);
        btnCount = (Button) findViewById(R.id.btnCount);
        count = (TextView) findViewById(R.id.countNumber);

        btnSay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("count", count.getText().toString());
                startActivity(intent);
            }
        });

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer currentValue = Integer.parseInt(count.getText().toString());
                count.setText(String.valueOf(currentValue+1));
            }
        });

    }
}