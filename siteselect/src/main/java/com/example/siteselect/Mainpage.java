package com.example.siteselect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Mainpage extends AppCompatActivity {

    private Object LocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        ActChangeSite();
        ActViewReport();
        ActViewRequest();
        ActNewRequest();
        ActNewReport();
        Signout(this);
    }

    private void ActNewReport(){
        Button Org = (Button) findViewById(R.id.btnReport);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(Mainpage.this, NewReport.class));
            }
        });
    }
    private void Signout(Context contex){
        Button Org = (Button) findViewById(R.id.btnReport);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                SignIn.CurrentEmployee.setEmpid(0);

                startActivity(new Intent(Mainpage.this, SignIn.class));
            }
        });
    }

    private void ActNewRequest(){
        Button Org = (Button) findViewById(R.id.btnmakerequest);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(Mainpage.this, NewRequest.class));
            }
        });
    }

    private void ActViewRequest(){
        Button Org = (Button) findViewById(R.id.Viewreports);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then toSelectProblem
                startActivity(new Intent(Mainpage.this, SelectProblem.class));
            }
        });
    }

    private void ActViewReport(){
        Button Org = (Button) findViewById(R.id.viewrequests);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then toRequestSupplies
                startActivity(new Intent(Mainpage.this, SelectRequestview.class));
            }
        });
    }
    private void ActChangeSite(){
        Button Org = (Button) findViewById(R.id.btnChangeSite);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                SignIn.CurrentEmployee.setsiteid(0);

                startActivity(new Intent(Mainpage.this, siteselect.class));
            }
        });
    }

}
