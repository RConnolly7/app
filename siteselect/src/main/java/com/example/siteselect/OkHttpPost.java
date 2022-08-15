package com.example.siteselect;

import android.widget.EditText;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpPost {
    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) throws Exception {

        OkHttpPost obj = new OkHttpPost();

    }
    public void test( ) throws Exception {

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
            System.out.println(response.body().string());
        }catch (IOException e) {
            System.out.println("IOException triggered");
            e.printStackTrace();
        }
        System.out.println("bad network query");
    }

    public String Signin(EditText Username,EditText Passwordtxt ) throws Exception {
        String usernametxt = Username.getText().toString();
        String tpswtxt = Passwordtxt.getText().toString();
        // form parameters
        RequestBody formBody = new MultipartBody.Builder()
                .addFormDataPart("username", usernametxt)
                .addFormDataPart("password", tpswtxt)
                .build();
        String URL  = "http://192.168.0.10:8079/Userlogwork.php?username="+usernametxt+"&password="+tpswtxt;
        System.out.println(URL);
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();
        System.out.println("sign in builder attempt pre");
        System.out.println(request.url());
        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println("beginning request");
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // Get response body
            System.out.println("response.body().string()");
            return response.body().string() ;
        }catch (IOException e) {
            System.out.println("IOException triggered");
            e.printStackTrace();
        }
        System.out.println("bad network query");
        return "error";
    }

    public String GetSites(){
        String URL  = "http://192.168.0.10:8079/getsites.php";
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("User-Agent", "OkHttp Bot")
                .build();
        try(Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);{
                System.out.println("Message recieved");
                return response.body().string() ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "defaulted";
    }

    public String SendReport(){
        String URL  = "http://192.168.0.10:8079/Newreport.php";
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("User-Agent", "OkHttp Bot")
                .build();
        try(Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);{
                System.out.println("Message recieved");
                return response.body().string() ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Standard Failure";
    }

}
