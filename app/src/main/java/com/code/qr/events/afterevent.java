package com.code.qr.events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;


public class afterevent extends ActionBarActivity {

    private WebView webView;
    private String str,cap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterevent);
        Intent intent = getIntent();
        str =intent.getStringExtra("Event");
        cap =intent.getStringExtra("caption");
        ( (TextView)(findViewById(R.id.event))).setText(cap);

        ImageButton btnback,btnhome;

        btnback =(ImageButton)findViewById(R.id.back);
        btnhome =(ImageButton)findViewById(R.id.home);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(afterevent.this,  mainscreen.class));
                finish();
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.equalsIgnoreCase("Aisle Finder")){
                    startActivity(new Intent(afterevent.this,  mainscreen.class));
                    finish();

                }else{
                    Intent i = new Intent(afterevent.this, exlusiveevent.class);
                    i.putExtra("Event", str);
                    startActivity(i);
                    finish();

                }

            }
        });
        WebViewClient yourWebClient = new WebViewClient()
        {
            // Override page so it's load on my view only
            @Override
            public boolean shouldOverrideUrlLoading(WebView  view, String  url)
            {
                // This line we let me load only pages inside Firstdroid Webpage
                if ( url.contains("google") == true )
                    // Load new URL Don't override URL Link
                    return false;

                // Return true to override url loading (In this case do nothing).
                return true;
            }
        };


        // Get Web view
        webView = (WebView) findViewById( R.id.webView ); //This is the id you gave
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);       //Zoom Control on web (You don't need this
        //if ROM supports Multi-Touch
        webView.getSettings().setBuiltInZoomControls(true); //Enable Multitouch if supported by ROM
        webView.setWebViewClient(yourWebClient);
        webView.setPadding(0, 0, 0, 0);
        webView.setInitialScale(getScale());
        // Load URL
        if(str.equalsIgnoreCase("Aisle Finder")) {
            webView.loadUrl("http://aakarshan22.wix.com/aisle");
        }
        else if(str.equalsIgnoreCase("All Offers")) {
            webView.loadUrl("http://aakarshan22.wix.com/offers");
        }
        else if(cap.equalsIgnoreCase("Special Offers")&& str.equalsIgnoreCase("Store 1")) {
            webView.loadUrl("http://aakarshan22.wix.com/s1so");
        }

        else if(cap.equalsIgnoreCase("General Offers")&& str.equalsIgnoreCase("Store 1")) {
            webView.loadUrl("http://aakarshan22.wix.com/s1go");
        }
        else if(cap.equalsIgnoreCase("Special Offers")&& str.equalsIgnoreCase("Store 2")) {
            webView.loadUrl("http://aakarshan22.wix.com/s2so");
        }

        else if(cap.equalsIgnoreCase("General Offers")&& str.equalsIgnoreCase("Store 2")) {
            webView.loadUrl("http://aakarshan22.wix.com/s2go");
        }   else if(cap.equalsIgnoreCase("Special Offers")&& str.equalsIgnoreCase("Store 3")) {
            webView.loadUrl("http://aakarshan22.wix.com/s3so");
        }

        else if(cap.equalsIgnoreCase("General Offers")&& str.equalsIgnoreCase("Store 3")) {
            webView.loadUrl("http://aakarshan22.wix.com/s3go");
        }
       else{
            webView.loadUrl("http://aakarshan22.wix.com/offers");
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            startActivity(new Intent(afterevent.this,  mainscreen.class));
            finish();
            return true;
        }
        if (id == R.id.action_back) {
            if(str.equalsIgnoreCase("Aisle Finder")){
                startActivity(new Intent(afterevent.this,  mainscreen.class));
                finish();
                return true;
            }else{
                Intent i = new Intent(afterevent.this, exlusiveevent.class);
                i.putExtra("Event", str);
                startActivity(i);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }
    private int getScale(){
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width)/new Double(200);
        val = val * 100d;
        return val.intValue();
    }

}
