package com.example.siteselect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class ReportConv extends AppCompatActivity {
    String ConvoCSV = "";
    //passthrough value
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String Convofindme = intent.getStringExtra("message_key");
        setContentView(R.layout.activity_report_conv);
        ActReturn();
        //determine which convo to select
        if(Convofindme!=""){
            try {
                KnownConvo(Convofindme);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                BlindCovo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void BlindCovo() throws Exception {

        HttpPost post = new HttpPost("https://httpbin.org/post");
        String EmpID = String.valueOf(SignIn.CurrentEmployee.getEmpid());
        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("UserID", EmpID));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));

        }

    }

    private void KnownConvo(String ConvoID) throws Exception {

        HttpPost post = new HttpPost("https://httpbin.org/post");
        String EmpID = String.valueOf(SignIn.CurrentEmployee.getEmpid());
        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("UserID", EmpID));
        urlParameters.add(new BasicNameValuePair("ConvoID", ConvoID));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
            ConvoCSV = "";
        }

    }


    private void ActReturn(){
        ImageButton Org = (ImageButton) findViewById(R.id.btnReturnPR);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(ReportConv.this, Mainpage.class));
            }
        });
    }
}