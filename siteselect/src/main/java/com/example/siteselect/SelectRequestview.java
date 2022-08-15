package com.example.siteselect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectRequestview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_requestview);
        ActReturn();
        ActSelectConvo();
        ActNewReport();
    }

    private void ActReturn(){
        ImageButton Org = (ImageButton) findViewById(R.id.btnReturnSelR);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(SelectRequestview.this, Mainpage.class));
            }
        });
    }
    private void ActSelectConvo(){
        Button Org = (Button) findViewById(R.id.BTNSelect);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(SelectRequestview.this, ReportConv.class));
            }
        });
    }
    private void ActNewReport(){
        Button Org = (Button) findViewById(R.id.BTNnew);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(SelectRequestview.this, NewRequest.class));
            }
        });
    }
}