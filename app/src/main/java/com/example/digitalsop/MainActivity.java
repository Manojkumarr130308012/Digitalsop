package com.example.digitalsop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.digitalsop.Config.DBHelper;
import com.example.digitalsop.Util.ImageLoader;
import com.github.barteksc.pdfviewer.PDFView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    //videoview
    VideoView videoView,videoView1;
    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
    int index = 0;
    String Screen="";
    String content_type="";
    String dataurl="";
    String dataurl1="";
    String last_update="";
    String string="";
    String last_update_new="";
  //pdfview
    PDFView pdfView,pdfView1;
    // url of our PDF file.
    String pdfurl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
    DBHelper dbHelper;
    String id,na,pa;
int i=0;
    //imageview
    String url = "https://api.androidhive.info/images/sample.jpg";
    ImageView image,image1;
    ProgressDialog mProgressDialog;

   LinearLayout lin,lin1;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    JSONObject resultJsonObject;
    TextView myTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        videoView = findViewById(R.id.videoview);
        videoView1 = findViewById(R.id.videoview1);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int height = displaymetrics.heightPixels;
//        int width = displaymetrics.widthPixels;
////        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) videoView.getLayoutParams();
//        params.width = width;
//        params.height=height;
//        params.setMargins(0, 0, 0, 0);
        dbHelper=new DBHelper(this);
        Cursor res = dbHelper.getAllData();


        while (res.moveToNext()) {
            id = res.getString(0);
            na = res.getString(1);
        }

        //lineralayout
        lin=findViewById(R.id.lin);
        lin1=findViewById(R.id.lin1);

       lin1.setVisibility(View.GONE);

        //videoview





        //pdfview
        pdfView = findViewById(R.id.idPDFView);
        pdfView1 = findViewById(R.id.idPDFView1);



        //imageview
        // Loader image - will be shown before loading image
        image=findViewById(R.id.image);
        image1=findViewById(R.id.image1);


        sendAndRequestResponse();
        myTextView = findViewById(R.id.dd);
        myTextView.setSingleLine(true);
        myTextView.setMarqueeRepeatLimit(-1);
        myTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        myTextView.setSelected(true);

    }


    class RetrivePDFfromUrl1 extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView1.fromStream(inputStream).load();
//            pdfView1.fromStream(inputStream).load();
        }
    }
    // create an async task class for loading pdf file from URL.
    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load();
//            pdfView1.fromStream(inputStream).load();
        }
    }

    private void sendAndRequestResponse() {
      //  https://neophron.in/erp/dsop/api
        String apiurl=""+na+"/get_content.php?mac_address="+getMacAddress();
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        //String Request initialized
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                apiurl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                  try {
                       resultJsonObject=new JSONObject(response.toString());


                      Screen = resultJsonObject.getString("screen_type");


                      final Handler handler = new Handler();
                      final Runnable runnable = new Runnable() {
                          @Override
                          public void run() {
                              sendjson();
                              handler.postDelayed(this,30000);//60 second delay
                              Log.e("test",""+i+1);
//                              Toast.makeText(MainActivity.this, "running", Toast.LENGTH_SHORT).show();
                          }
                      };handler.postDelayed(runnable,30000);

                       } catch (JSONException e) {
                       Log.e("JSON", "There was an error parsing the JSON", e);
                       }

        if (Screen.equals("1")){
              lin.setVisibility(View.VISIBLE);
              lin1.setVisibility(View.GONE);
            try {
                content_type = resultJsonObject.getString("content_type");
                dataurl = resultJsonObject.getString("url");
                last_update = resultJsonObject.getString("last_update");
                string = resultJsonObject.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }
          if (!string.equals("null")){

              myTextView.setText(""+string);

          }else{
              myTextView.setVisibility(View.GONE);
          }

        if (content_type.equals("1")){
         MediaController mediacontroller = new MediaController(MainActivity.this);
        mediacontroller.setAnchorView(videoView);
        videoView.setMediaController(mediacontroller);
        videoView.setVideoURI(Uri.parse(""+dataurl));
        videoView.requestFocus();
        videoView.start();
        videoView.setVisibility(View.VISIBLE);
        pdfView.setVisibility(View.GONE);
        image.setVisibility(View.GONE);
        String finalUrl = dataurl;
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
       @Override
       public void onCompletion(MediaPlayer mp) {
//        Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
        if (index++ == arrayList.size()) {
        index = 0;
        mp.release();
//        Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
        } else {
        videoView.setVideoURI(Uri.parse(""+ finalUrl));
        videoView.start();
        }


        }
        });

//        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//    @Override
//    public boolean onError(MediaPlayer mp, int what, int extra) {
//        Log.d("API123", "What " + what + " extra " + extra);
//        return false;
//        }
//        });

        }
        else if(content_type.equals("3")){
        new RetrivePDFfromUrl().execute(dataurl);
        videoView.setVisibility(View.GONE);
        pdfView.setVisibility(View.VISIBLE);
        image.setVisibility(View.GONE);
        }
        else if(content_type.equals("2")){
            Log.e("dddddd",""+dataurl);
        Picasso.with(getApplicationContext()).load(dataurl).into(image);

        videoView.setVisibility(View.GONE);
        pdfView.setVisibility(View.GONE);
        image.setVisibility(View.VISIBLE);
        }



        }else if(Screen.equals("2")){
            lin.setVisibility(View.GONE);
            videoView.setVisibility(View.GONE);
            lin1.setVisibility(View.VISIBLE);
            try {
                content_type = resultJsonObject.getString("content_type");
                dataurl = resultJsonObject.getString("url");
                dataurl1 = resultJsonObject.getString("url1");
                last_update = resultJsonObject.getString("last_update");
                string = resultJsonObject.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (!string.equals("null")){
                myTextView.setText(""+string);

            }else{
                myTextView.setVisibility(View.GONE);
            }

            if (content_type.equals("2")){
                final MediaController mediacontroller = new MediaController(MainActivity.this);
                mediacontroller.setAnchorView(videoView1);
                videoView1.setMediaController(mediacontroller);
                videoView1.setVideoURI(Uri.parse(""+dataurl));
                videoView1.requestFocus();
                videoView1.start();
                videoView1.setVisibility(View.VISIBLE);
                pdfView1.setVisibility(View.VISIBLE);
                image1.setVisibility(View.GONE);
                String finalUrl = dataurl;
                videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
//        Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                        if (index++ == arrayList.size()) {
                            index = 0;
                            mp.release();
//        Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                        } else {
                            videoView1.setVideoURI(Uri.parse(""+ finalUrl));
                            videoView1.start();
                        }


                    }
                });

                videoView1.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Log.d("API123", "What " + what + " extra " + extra);
                        return false;
                    }
                });
                new RetrivePDFfromUrl1().execute(dataurl1);
//                videoView.setVisibility(View.GONE);
//                pdfView.setVisibility(View.VISIBLE);
//                image.setVisibility(View.GONE);
            }
            else if(content_type.equals("3")){
                new RetrivePDFfromUrl1().execute(dataurl);
                Picasso.with(getApplicationContext()).load(dataurl1).into(image1);

                videoView1.setVisibility(View.GONE);
                pdfView1.setVisibility(View.VISIBLE);
                image1.setVisibility(View.VISIBLE);
            }
            else if(content_type.equals("1")){
                final MediaController mediacontroller = new MediaController(MainActivity.this);
                mediacontroller.setAnchorView(videoView1);
                videoView1.setMediaController(mediacontroller);
                videoView1.setVideoURI(Uri.parse(""+dataurl));
                videoView1.requestFocus();
                videoView1.start();
                videoView1.setVisibility(View.VISIBLE);
                pdfView1.setVisibility(View.GONE);
                image1.setVisibility(View.VISIBLE);
                String finalUrl = dataurl;
                videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
//        Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                        if (index++ == arrayList.size()) {
                            index = 0;
                            mp.release();
//        Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                        } else {
                            videoView1.setVideoURI(Uri.parse(""+ finalUrl));
                            videoView1.start();
                        }


                    }
                });

                videoView1.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Log.d("API123", "What " + what + " extra " + extra);
                        return false;
                    }
                });
//                new RetrivePDFfromUrl1().execute(dataurl1);
                Picasso.with(getApplicationContext()).load(dataurl1).into(image1);


            }





        }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog

            }
        });

        // Adding request to request queue


        mRequestQueue.add(jsonObjReq);
    }




    private void sendjson() {
        String apiurl=""+na+"/get_content.php?mac_address="+getMacAddress();

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(MainActivity.this);
        //String Request initialized
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                apiurl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject resultJsonObject=new JSONObject(response.toString());


                    last_update_new = resultJsonObject.getString("last_update");
                    Log.e("last_update_new",""+last_update_new);
                    Log.e("last_update",""+last_update);
                    if (!last_update.equals(last_update_new))
                    {
                        Log.e("last_update_new",""+last_update_new);
                        Log.e("last_update",""+last_update);

                        last_update=last_update_new;
                        sendAndRequestResponse();
//                        Toast.makeText(MainActivity.this, "MainActivity", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    Log.e("JSON", "There was an error parsing the JSON", e);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog

            }
        });

        // Adding request to request queue


        mRequestQueue.add(jsonObjReq);
    }

    public String getMacAddress(){
        try{
            List<NetworkInterface> networkInterfaceList = Collections.list(NetworkInterface.getNetworkInterfaces());
            String stringMac = "";
            for(NetworkInterface networkInterface : networkInterfaceList)
            {
                if(networkInterface.getName().equalsIgnoreCase("wlon0"));
                {
                    for(int i = 0 ;i <networkInterface.getHardwareAddress().length; i++){
                        String stringMacByte = Integer.toHexString(networkInterface.getHardwareAddress()[i]& 0xFF);
                        if(stringMacByte.length() == 1)
                        {
                            stringMacByte = "0" +stringMacByte;
                        }
                        stringMac = stringMac + stringMacByte.toUpperCase() + ":";
                    }
                    break;
                }
            }
            return stringMac;
        }catch (SocketException e)
        {
            e.printStackTrace();
        }
        return  "0";
    }
}


