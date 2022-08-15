package com.example.siteselect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class Login extends AppCompatActivity {
    //public static TrackingInfoClass CurrentEmployee = new TrackingInfoClass("editText.getText().toString()");
    public int employeeid;
    Uri uri = new Uri.Builder()
            .scheme("http")
            .authority("foo.com")
            .path("someservlet")
            .appendQueryParameter("param1", "foo")
            .appendQueryParameter("param2", "bar")
            .build();

    private boolean sendPost() throws Exception {
        EditText editTexta = (EditText)findViewById(R.id.txtuser);
        String usernametxt = editTexta.getText().toString();
        EditText editpswtxt = (EditText)findViewById(R.id.editTextTextPassword);
        String tpswtxt = editTexta.getText().toString();
        HttpPost post = new HttpPost("http://host.docker.internal:8079/GetAllEmployees.php");

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username",usernametxt ));
        urlParameters.add(new BasicNameValuePair("password", tpswtxt));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            if (EntityUtils.toString(response.getEntity())!="") {
                int set = Integer.parseInt(EntityUtils.toString(response.getEntity()));
                //CurrentEmployee.setEmpid(set);
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }



        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            if(EntityUtils.toString(response.getEntity()) == "success for user "+usernametxt){
               // CurrentEmployee.setempnm(usernametxt);

                return true;
            }else{
                System.out.println(EntityUtils.toString(response.getEntity()));
                return false;
            }
        }

    }

    private void ActSiteselect(){
        Button Org = (Button) findViewById(R.id.btnsignin);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loginflag = false;
                try {
                    loginflag = sendPost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(loginflag==true) {
                    //declare which screen to change from and then to
                    EditText editText = (EditText)findViewById(R.id.txtuser);
                    String empname = (editText.getText().toString());
                    //CurrentEmployee.setempnm(empname);
                    startActivity(new Intent(Login.this, Siteselecy.class));
                }else{
                    //login error
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //btnsignin

        setContentView(R.layout.activity_login);
        ActSignin();
    }
    private void ActSignin(){
        Button Org = (Button) findViewById(R.id.btnsignin);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //declare which screen to change from and then to
                ActSiteselect();
            }


        });
    }
}