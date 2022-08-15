package com.example.siteselect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectProblem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_problem);
        ActReturn();
        ActSelectConvo();
        ActNewReport();
    }

    private void ActReturn(){
        ImageButton Org = (ImageButton) findViewById(R.id.btnReturnPS);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(SelectProblem.this, Mainpage.class));
            }
        });
    }
    private void ActSelectConvo(){
        Button Org = (Button) findViewById(R.id.BTNSelect5);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(SelectProblem.this, ReportConv.class));
            }
        });
    }
    private void ActNewReport(){
        Button Org = (Button) findViewById(R.id.BTNnew5);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(SelectProblem.this, NewReport.class));
            }
        });
    }
}