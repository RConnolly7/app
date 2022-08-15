package com.example.siteselect;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Siteselecy extends AppCompatActivity {
    /*
//this function does nothing... but crash the program fool.
    public String sendPost() throws Exception {
        HttpPost post = new HttpPost("https://httpbin.org/post");

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            if(EntityUtils.toString(response.getEntity()) != ""){
                return "oh snap";
            }else{
                System.out.println(EntityUtils.toString(response.getEntity()));
                return EntityUtils.toString(response.getEntity());
            }
        }

    }
    */
    //this gets all the sites
    public void sendPost(View.OnClickListener supercontext) throws Exception{
        OkHttpPost obj = new OkHttpPost();
        Handler viewhandler;
        Handler handler = new Handler(Looper.getMainLooper());
        Boolean waiting = false;
        LinearLayout ll = (LinearLayout) findViewById(R.id.SiteLinearLay);
                //new LinearLayout(this);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            //make the request here
            String message = obj.GetSites();
            String sites[] = message.split(",");
            System.out.println("This is the message "+message);
            System.out.println("This is the sites "+sites.length);

            handler.post(() -> {
                System.out.println("This is the sites second time around "+sites.length);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.siteitem, null);
                // Find the ScrollView
                ScrollView svs = (ScrollView) v.findViewById(R.id.siteviewsel);
                // Create a LinearLayout element

                ll.setOrientation(LinearLayout.VERTICAL);
                //process getsites results
                for (int i = 0; i < 0/*sites.length-1*/; i= i +4) {
                    System.out.println("this is the loop counter  "+i);
                    //current site's name
                    String currentsite = sites[i+1];
                    TextView tv = new TextView(this);
                    //set the name of the current site
                    tv.setTag(sites[i]);
                    if(currentsite.equals("")){
                        currentsite="Unnamed";
                    }
                    tv.setText(currentsite);
                    System.out.println("the tv text "+tv.getText().toString());
                    tv.setId(View.generateViewId());
                    tv.setWidth(200);
                    tv.setHeight(50);
                    tv.setVisibility(View.VISIBLE);
                    tv.setBackgroundColor(i*1000);

                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //associate
                            TextView OriginView = (TextView) view;
                            String convertme = OriginView.getTag().toString();
                            SignIn.CurrentEmployee.setsiteid(Integer.valueOf(convertme));
                            //name
                            SignIn.CurrentEmployee.setcurrentsitenm(OriginView.getText().toString());
                            //we seek to achieve this by ensuring the value is tranfered through an array
                            //to this end it would be to find a way to attach a value to object itself
                        }
                    });
                    //add to linear view
                   // ll.addView(tv);
                    ConstraintLayout topman = (ConstraintLayout) findViewById(R.id.SiteSelectConstraint);
                    //topman.addView(tv);
                    TextView textView = new TextView(this);
                    System.out.println("the tv text "+tv.getText().toString());
                    textView.setText("TextView " + String.valueOf(i));
                    System.out.println("the textview text "+textView.getText().toString());
                    ll.addView(textView);
                    setContentView(R.layout.activity_siteselecy);
                }
                // Add the LinearLayout element to the ScrollView

                svs = (ScrollView) findViewById(R.id.siteviewsel);
                System.out.println(" "+svs);


                // Display the view
                setContentView(v);

            });
        });

        while (waiting = false){

        }
        ;
    }
    //this reads the csv from the server and populates an array
    public ArrayList<ArrayList<String>> csvreader(String InStr){
        ArrayList<ArrayList<String> > Div = new ArrayList<ArrayList<String> >();

        int whicharray =0;
        int count=0;
        String[] defaulted = {"Connection error","failure to connect to server"};
        Scanner CSV = new Scanner(InStr);
        CSV.useDelimiter(",");
        while (CSV.hasNext())  //returns a boolean value
        {
            Div.get(whicharray).add(CSV.next());
            count = count + 1;
            if(count%2==0){
                whicharray = whicharray+1;
            }
        }
        return Div;
        /*
        String[] Namesout = new String[whicharray];
        for(int i =0; i<whicharray+1;i++){
            Namesout[i]= Div.get(i).get(1);
        }
        if(Namesout!=null){
            return Namesout;
        }
        return defaulted;
        */
    }
    //get the sites with a post request
    public static void main(String[] args) throws IOException {

    }

    LocationRequest mLocationRequest;
    Location mCurrentLocation;
    String mLastUpdateTime;

    public void bigbutton(Context subaru) throws Exception {
        Button add = (Button) findViewById(R.id.btnSiteconfirm);
        add.setOnClickListener(new View.OnClickListener() {
            ConstraintLayout topman = (ConstraintLayout) findViewById(R.id.SiteSelectConstraint);
            //topman.addView(tv);


            @Override
            public void onClick(View view) {

                try {
                    sendPost(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

public void sim(){
    LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayoutbogaloo);
    TextView textView = new TextView(this);
    textView.setText("TextView ttt" );
    System.out.println("the textview text "+textView.getText().toString());
    ll.addView(textView);
    setContentView(R.layout.activity_siteselecy);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int[] UserSiteSelect = {-1};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siteselecy);
        sim();
        sim();
        sim();
        LinearLayout ll = (LinearLayout) findViewById(R.id.SiteLinearLay);
        TextView textView = new TextView(this);
        textView.setText("TextView ttt" );
        System.out.println("the textview text "+textView.getText().toString());
        ll.addView(textView);
        setContentView(R.layout.activity_siteselecy);

        try {
            sim();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ActMainPage();

        // Find the ScrollView

        // Create a LinearLayout element
        /*
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        TextView texto = new TextView(this);
        texto.setText("textovission");
        ll.addView(texto);
        svs.addView(ll);
         */
        Context passing = Siteselecy.this;
        try {
            //sendPost(passing);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Add text

        //populates

        // Add the LinearLayout element to the ScrollView

        // Display the view

/*
        SitesContainer imsite = new SitesContainer();
        imsite = SitesContainer.dummylist(imsite);
        for (int i = 0; i == imsite.getSiteList().size(); i++) {
            String currentsite = imsite.getSiteList().get(i).OutName();
            TextView tv = new TextView(this);
            tv.setText(currentsite);
            int finalI = i;

            SitesContainer finalImsite = imsite;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserSiteSelect[0] = finalImsite.getSiteList().get(finalI).getId();
                }
            });
            ll.addView(tv);
        }


 */


    //check build version
        if (Build.VERSION.SDK_INT >= 23) {
            //get permission if tracking would work
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                begintrack();
            }
        } else {
            begintrack();
        }
    }
    void begintrack(){
        Intent intent =  new Intent(Siteselecy.this , LocationServices.class);
        startService(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 1:
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                begintrack();
            }else{
                Toast.makeText(this,"Tracking is required for app functionality",Toast.LENGTH_LONG);
            }
        }
    }

    private void ActMainPage(){
        Button Org = (Button) findViewById(R.id.btnSiteconfirm);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(Siteselecy.this, Mainpage.class));
            }
        });
    }


}