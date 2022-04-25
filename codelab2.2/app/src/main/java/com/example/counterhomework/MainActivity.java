package com.example.counterhomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btnCount;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        btnCount = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editTextTextPersonName);

        // init
        if(savedInstanceState != null){
            editText.setText(savedInstanceState.getString("edit_text"));
        }
    }


    public void btnClickCount(View v){
        Integer c = Integer.parseInt(tv.getText().toString());
        tv.setText(String.valueOf(c+1));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("edit_text", editText.getText().toString());
    }
}