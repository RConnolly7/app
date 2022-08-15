package com.example.sitetrack;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button submitbtn = (Button) findViewById(R.id.btnorgsel);
/*
        public void onClick(View v) {
            Texthere = text.getText().toString();
            Intent intent = new Intent(FirstActivity.this,
                    SecondActivity.class);
            intent.putExtra("Text",Texthere);
            startActivity(intent);
        }
*/
    }
}
