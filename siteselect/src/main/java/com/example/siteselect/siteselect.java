package com.example.siteselect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.siteselect.databinding.ActivitySiteselectBinding;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class siteselect extends AppCompatActivity {
    public Boolean chosen = false;
    private AppBarConfiguration appBarConfiguration;
    private ActivitySiteselectBinding binding;

    public void bigbutton() throws Exception {
        Button add = (Button) findViewById(R.id.btnSiteconfirm);
        add.setOnClickListener(new View.OnClickListener() {
            ConstraintLayout topman = (ConstraintLayout) findViewById(R.id.SiteSelectConstraint);

            @Override
            public void onClick(View view) {
                try {
                   // sendPost(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySiteselectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {bigbutton();} catch (Exception e) {e.printStackTrace();}

        try {
            sendPost(this);
        } catch (Exception e) {
            e.printStackTrace();
        }



        binding.siteselectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseSite(view);

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_siteselect);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void chooseSite(View view){
        if (chosen==true){
                Intent intent =  new Intent(this , LocationServices.class);
                startService(intent);
                startActivity(new Intent(siteselect.this, Mainpage.class));
        }else{
            Snackbar.make(view, "Choose a site", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    public void sendPost(Context reroute) throws Exception{
        OkHttpPost obj = new OkHttpPost();
        Handler handler = new Handler(Looper.getMainLooper());
        LinearLayout ll = (LinearLayout) findViewById(R.id.theLinearLayout);
        //new LinearLayout(this);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            //make the request
            String message = obj.GetSites();
            String sites[] = message.split(",");

            handler.post(() -> {
                System.out.println("This is the sites second time around "+sites.length);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.siteitem, null);
                ll.setOrientation(LinearLayout.VERTICAL);
                //process getsites results
                for (int i = 0; i < sites.length; i= i +4) {
                    System.out.println("this is the loop counter  "+i);
                    //current site's name
                    String currentsite = sites[i+1];
                    TextView tv = new TextView(reroute);
                    //set the name of the current site
                    tv.setTag(sites[i]);
                    System.out.println("the tv tag is "+tv.getTag().toString()+ " The sites[i] -> "+ sites[i]);
                    if(currentsite.equals("")){
                        currentsite="Unnamed";
                    }
                    tv.setText(currentsite);
                    System.out.println("the tv text "+tv.getText().toString());
                    tv.setId(View.generateViewId());
                    tv.setWidth(200);
                    //tv.setHeight(110);
                    tv.setPadding(5,5,5,5);
                    tv.setTextSize(30);
                    tv.setVisibility(View.VISIBLE);
                    tv.setBackgroundColor(i*1000);

                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //associate
                            TextView OriginView = (TextView) view;
                            String convertme = OriginView.getTag().toString();
                            SignIn.CurrentEmployee.setsiteid(Integer.valueOf(convertme));
                            SignIn.CurrentEmployee.setsiteid(Integer.parseInt(convertme));

                            //name
                            SignIn.CurrentEmployee.setcurrentsitenm(OriginView.getText().toString());
                            TextView changer = (TextView) findViewById(R.id.CurrentSiteSelected);
                            changer.setText(OriginView.getText().toString());
                            chosen = true;
                            //we seek to achieve this by ensuring the value is tranfered through an object
                            //to this end it would be to find a way to attach a value to object itself
                        }
                    });
                    //add to linear view
                    ll.addView(tv);
                    ConstraintLayout topman = (ConstraintLayout) findViewById(R.id.SiteSelectConstraint);
                    //topman.addView(tv);

                }
            });
        });
    }
}