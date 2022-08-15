package com.example.siteselect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  OrgSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_select);
        //initialise listener
        ActSignin();
    }
    private void ActSignin(){
        Button Org = (Button) findViewById(R.id.btnorgsel);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(OrgSelect.this, Login.class));
            }
        });
    }
}
