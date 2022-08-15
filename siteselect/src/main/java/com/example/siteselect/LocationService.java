package com.example.siteselect;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

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

@SuppressWarnings("ALL")
public class LocationService extends Service {
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback locationCallback;
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                String passthrough =locationResult.getLastLocation().getLatitude() +"," +locationResult.getLastLocation().getLongitude();
                Log.d("location logging", "Latitude: "+ locationResult.getLastLocation().getLatitude()+" "+"Longtitude: "+ locationResult.getLastLocation().getLongitude());
                try {
                    exportLocationToServer(passthrough);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        requestLocation();
        return super.onStartCommand(intent, flags, startId);
    }

    @SuppressLint("MissingPermission")
    private void requestLocation(){
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
        locationRequest.setInterval(1800000);

    }

    @Override
    public void onDestroy() {


    }
    //post the current employee item from login to allow for the co-ordinates to be make from each track
        private void exportLocationToServer(String location) throws Exception {
            //ADD POST LOCATION
            HttpPost post = new HttpPost("192.168.56.1:8079/Userloclogsingle.php");
            SignIn.CurrentEmployee.getEmpid();
            // add request parameter, form parameters
            List<NameValuePair> urlParameters = new ArrayList<>();

            String.valueOf(SignIn.CurrentEmployee.getsiteid());
            //encode values
            urlParameters.add(new BasicNameValuePair("userid", String.valueOf(SignIn.CurrentEmployee.getEmpid())));
            urlParameters.add(new BasicNameValuePair("Siteid", String.valueOf(SignIn.CurrentEmployee.getsiteid())));
            urlParameters.add(new BasicNameValuePair("Location", location));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                System.out.println(EntityUtils.toString(response.getEntity()));
            }

        }

    }

