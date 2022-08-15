package com.example.siteselect;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NewReport extends AppCompatActivity {
    int SELECT_PICTURE = 200;
    //ImageButton BSelectImage = (ImageButton) findViewById(R.id.BTNImage);
    Bitmap saveme;
    String ImageTextFormat = "";
    String imageLoc="NoImageSelectedByUserForThisSiteTrackProcessIfSomeoneNamesTheirFileThisItWillCauseProblemsHenceWhyThisIsSoLong";

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    try {
                        saveme =  MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageUri);
                        TextView label = (TextView)findViewById(R.id.lblImageName);
                        Cursor returnCursor = getContentResolver().query(selectedImageUri, null, null, null, null);
                        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                        int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                        returnCursor.moveToFirst();
                        label.setText("Photo loaded successfully "+returnCursor.getString(nameIndex));
                        imageLoc = getPath(getApplicationContext(),selectedImageUri);

                    } catch (IOException e) {
                        e.printStackTrace();
                        TextView label = (TextView)findViewById(R.id.lblImageName);
                        label.setText("failed load CODE ERROR, Error code:011");
                    }

                }
            }
        }
    }
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    // Handle the returned Uri
                }
            });



    static final int REQUEST_IMAGE_GET = 1;

    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }
    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report);
        ActMadeReport();
        ActReturn();
        ImagebuttonListnerRepo();
    }

    private void ImagebuttonListnerRepo(){
        ImageButton BSelectImage = (ImageButton) findViewById(R.id.BTNImage);
        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void ActMadeReport(){
        TextInputLayout textreport = (TextInputLayout) findViewById(R.id.textInputLayout5);
        Button Org = (Button) findViewById(R.id.BTNSend2);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //declare which screen to change from and then to
                if(textreport.getEditText().getText().toString().trim() != "") {
                    String usertext = textreport.getEditText().getText().toString().trim();
                    doInBackground();
                    startActivity(new Intent(NewReport.this, ReportConv.class));
                }
            }
        });
    }
    /*
    private void uploadimage(){
        //take photo and hold it
        ImageButton Org = (ImageButton) findViewById(R.id.BTNImage);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bring up image upload interface
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);

                ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == Activity.RESULT_OK) {
                                    // There are no request codes
                                    Intent data = result.getData();
                                    doSomeOperations();
                                }
                            }
                        });


                    mGetContent.launch("image/*");
                    //upload image
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");



            }
        });
    }
*/
    private void ActReturn(){
        ImageButton Org = (ImageButton) findViewById(R.id.btnReturn);
        Org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare which screen to change from and then to
                startActivity(new Intent(NewReport.this, Mainpage.class));
            }
        });
    }
//the example post request DNU
    private void sendPost(String Text) throws Exception {

        HttpPost post = new HttpPost("http://host.docker.internal:8079/GetAllEmployees.php");

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", "abc"));
        urlParameters.add(new BasicNameValuePair("password", "123"));
        urlParameters.add(new BasicNameValuePair("custom", "secret"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        }

    }

    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                String storageDefinition;

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                } else {

                    if (Environment.isExternalStorageRemovable()) {
                        storageDefinition = "EXTERNAL_STORAGE";
                    } else{
                        storageDefinition = "SECONDARY_STORAGE";
                    }

                    return System.getenv(storageDefinition) + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private void upload() {
        // Image location URL
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        saveme.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte[] ba = bao.toByteArray();
        ImageTextFormat = Base64.encodeToString(ba,Base64.DEFAULT);
        // Upload image to server
    }
    protected String doInBackground() {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if(imageLoc != "NoImageSelectedByUserForThisSiteTrackProcessIfSomeoneNamesTheirFileThisItWillCauseProblemsHenceWhyThisIsSoLong") {
            TextView label = (TextView)findViewById(R.id.lblImageName);
            String thename = label.getText().toString();
            upload();
            nameValuePairs.add(new BasicNameValuePair("base64", ImageTextFormat));
            nameValuePairs.add(new BasicNameValuePair("ImageName", System.currentTimeMillis()+"," + thename +".jpg"));
        }else{
            nameValuePairs.add(new BasicNameValuePair("base64", "NoImageUploaded"));
            nameValuePairs.add(new BasicNameValuePair("ImageName", "NoImageUploaded"));
        }
        TextInputLayout usermessage = (TextInputLayout) findViewById(R.id.textInputLayout5);
        String sendmessage = usermessage.getEditText().getText().toString().trim();
        nameValuePairs.add(new BasicNameValuePair("UserID", Integer.toString(SignIn.CurrentEmployee.getEmpid())));
        nameValuePairs.add(new BasicNameValuePair("UserSite", Integer.toString(SignIn.CurrentEmployee.getsiteid())));
        nameValuePairs.add(new BasicNameValuePair("Message", sendmessage ));

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost( "192.168.56.1:8079/Newreport.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            String st = EntityUtils.toString(response.getEntity());
            Log.v("log_tag", "In the try Loop" + st);

        } catch (Exception e) {
            Log.v("log_tag", "Error in http connection " + e.toString());
        }
        return "Success";

    }
        //second call
        public String sendthereport(){
        String TheImagename = "";
        String Base64Image = "NoImageUploaded";
        if(imageLoc != "NoImageSelectedByUserForThisSiteTrackProcessIfSomeoneNamesTheirFileThisItWillCauseProblemsHenceWhyThisIsSoLong") {
                TextView label = (TextView)findViewById(R.id.lblImageName);
                String thename = label.getText().toString();
                upload();
                Base64Image=ImageTextFormat;
                TheImagename = System.currentTimeMillis()+"," + thename +".jpg";
            }else{
                Base64Image =  "NoImageUploaded";
                TheImagename = "NoImageUploaded";
            }

        return "Success";
        }
}


