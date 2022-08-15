package com.example.siteselect;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.siteselect.databinding.ActivityRequestSuppliesBinding;
public class RequestSupplies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_supplies);
        ActReturn();
    }
    private void ActReturn(){
        ImageButton Org = (ImageButton) findViewById(R.id.btnReturnSR);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(RequestSupplies.this, Mainpage.class));
            }
        });
    }
}