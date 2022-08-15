package com.example.siteselect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class SignIn extends AppCompatActivity {
    //we need a new post method brothers
    public static TrackingInfoClass CurrentEmployee;

    static {
        CurrentEmployee = new TrackingInfoClass("editText.getText().toString()");
    }

    private boolean sendPoster() throws Exception {
        EditText editText = (EditText) findViewById(R.id.txtuser);
        String usernametxt = editText.getText().toString();
        EditText editpswtxt = (EditText) findViewById(R.id.editTextTextPassword);
        String tpswtxt = editText.getText().toString();
        HttpPost post = new HttpPost("http://host.docker.internal:8079/Userlogwork.php");

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", usernametxt));
        urlParameters.add(new BasicNameValuePair("password", tpswtxt));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            if (EntityUtils.toString(response.getEntity()) == "success") {
                return true;
            } else {
                System.out.println(EntityUtils.toString(response.getEntity()));
                return false;
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //create on click listner
        ActSiteselect();
    }

    private void ActSiteselect() {
        Button Org = (Button) findViewById(R.id.btnsignin);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loginflag = false;
                try {
                    loginflag = SendDetails();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (loginflag == true) {
                    //declare which screen to change from and then to
                    System.out.println("moving to next screen");
                    startActivity(new Intent(SignIn.this, Siteselecy.class));
                } else {
                    //login error
                    System.out.println("Login flag is false");
                }
            }
        });
    }
    public void SignInDetails() throws Exception {
        /*
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("message", "Your message")
                .build();
        Request request = new Request.Builder()
                .url("https://www.example.com/index.php")
                .post(formBody)
                .build();

        try {
            Response response = client.newCall(request).execute();

            System.out.println(response.body().string());
            return true;
            // Do something with the response.
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

         */
    }
        public boolean SendDetails() throws Exception {
        OkHttpPost obj = new OkHttpPost();
        //obj.test();
            Boolean flag = false;
            AtomicReference<String> flago = new AtomicReference<>("None");
            EditText editText = (EditText) findViewById(R.id.txtuser);
            EditText editpswtxt = (EditText) findViewById(R.id.editTextTextPassword);
            System.out.println("sign in run");
            new Thread((Runnable) () -> {
                try {
                    flago.set(obj.Signin(editText, editpswtxt));
                    System.out.println("speaking with server");
                    String[] output = flago.get().toString().split(",");
                    System.out.println(output[1]);
                    System.out.println(flago.toString());
                    if(output[0].equals("success")){
                        CurrentEmployee.setEmpid(Integer.parseInt(output[1]));
                        startActivity(new Intent(SignIn.this, siteselect.class));
                    }else{
                        System.out.println("failed request");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
            if (flago.get() == "success") {
                //declare which screen to change from and then to
                System.out.println("moving to next screen");
                startActivity(new Intent(SignIn.this, Siteselecy.class));
            }
            System.out.println("thread ignored");
            return false;


            /*
            OkHttpClient httpClient = new OkHttpClient();
            RequestBody formBody = new MultipartBody.Builder()
                    .addFormDataPart("username", "test")
                    .addFormDataPart("password", "test")
                    .build();

            Request request = new Request.Builder()
                    .url("http://192.168.0.10:8079/test.php")
                    .addHeader("User-Agent", "OkHttp Bot")
                    .post(formBody)
                    .build();
            System.out.println("sign in builder attempt pre");
            System.out.println(request.url());
            try (Response response = httpClient.newCall(request).execute()) {
                System.out.println("beginning request");
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                // Get response body
               // System.out.println(response.body().string());
            }catch (IOException e) {
                System.out.println("IOException triggered");
                e.printStackTrace();
            }
            System.out.println("bad network query");
            EditText editText = (EditText) findViewById(R.id.txtuser);
            EditText editpswtxt = (EditText) findViewById(R.id.editTextTextPassword);
            System.out.println("testing");
            return obj.Signin(editText,editpswtxt);
            */
        }
        public void testing(){

        }

    private void postDataUsingVolley(String name, String job) {
        // url to post our data
        String url = "http://192.168.0.10:8079/test.php";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(SignIn.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                // on below line we are displaying a success toast message.
                Toast.makeText(SignIn.this, "Data added to API", Toast.LENGTH_SHORT).show();
                try {
                    // on below line we are parsing the response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    // below are the strings which we
                    // extract from our json object.
                    String name = respObj.getString("name");
                    String job = respObj.getString("job");

                    // on below line we are setting this string s to our text view.
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(SignIn.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("name", name);
                params.put("job", job);

                // at last we are
                // returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}



