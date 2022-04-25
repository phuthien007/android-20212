package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tv;
    public static final String EXTRA_REPLY =
            "com.example.android.twoactivities.extra.REPLY";
    EditText mReply;
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mReply = (EditText) findViewById(R.id.editText_second);
        tv = (TextView) findViewById(R.id.text_header);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        tv.setText(message);
    }

    public void returnReply(View view){
        String reply = mReply.getText().toString();
        Intent replyIntent = new Intent();
        Log.d(LOG_TAG, "End SecondActivity");
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK,replyIntent);
        finish();
    }
}