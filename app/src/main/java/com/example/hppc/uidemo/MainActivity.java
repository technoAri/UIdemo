package com.example.hppc.uidemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.graphics.Color.BLACK;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int READ_REQUEST_CODE = 42;
    private GifImageView gifImageView;
    FloatingActionButton actionButton;
    LinearLayout linearLayout;
    ImageView backButton;
    TextView tvTitle,smallText, largeText;
    private float start = (float) 92.3;
    private float end = (float) 108.9;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gifImageView = (GifImageView) findViewById(R.id.GifImageView);
        gifImageView.setGifImageResource(R.drawable.gifimage3);

        actionButton = (FloatingActionButton) findViewById(R.id.fabutton);
        //actionButton.setVisibility(View.INVISIBLE);
        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setVisibility(View.INVISIBLE);

        tvTitle = (TextView) findViewById(R.id.tvHeading);
        smallText = (TextView) findViewById(R.id.tvsmalltxt);
        largeText = (TextView) findViewById(R.id.tvLargetxt);
        linearLayout = (LinearLayout) findViewById(R.id.ll1);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionButton.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.VISIBLE);
                gifImageView.setGifImageResource(R.drawable.gifimage2);
                tvTitle.setText("Time Power");
                smallText.setText("The last 12 hours average\nelectricity consimption");
                largeText.setText("92.3");

//                new Thread(new Runnable() {
//
//                    public void run() {
//                        while (end>start) {
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                            }
//                            largeText.post(new Runnable() {
//
//                                public void run() {
//                                    largeText.setText("" + start);
//
//                                }
//
//                            });
//                            end--;
//                        }
//
//                    }
//
//                }).start();
                linearLayout.setBackgroundColor(Color.parseColor("#424153"));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("The Current Chart");
                smallText.setText("Total Electrical Consumption\nof Galaxy SOHO");
                largeText.setText("108.9");
                gifImageView.setGifImageResource(R.drawable.gifimage3);
                backButton.setVisibility(View.INVISIBLE);
                actionButton.setVisibility(View.VISIBLE);
                linearLayout.setBackgroundColor(Color.parseColor("#1b1b1c"));
            }
        });

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // External Storage permission has not been granted.
            requestPermission();
        }

    }

    public void getFile() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                Uri uri = resultData.getData();
                Log.i(TAG, "Uri: " + uri.toString());
                gifImageView.setGifImageUri(uri);
            }
        }
    }

    private void requestPermission() {
        Log.i(TAG, "External Storage permission has NOT been granted. Requesting permission.");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }
}

