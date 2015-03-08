package com.code.qr.events;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.code.qr.events.process.Folders;

import java.io.File;


public class splash extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }catch (Exception c){
            c.printStackTrace();
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        //TODO service initiate with shared preferences
        new Thread(new Runnable(){

			@Override
			public void run() {
				// Create Folders
				Folders.createDirectory(splash.this);
				if(new File(Folders.dir).list().length>0)
				Folders.createList(Folders.dir);
				
			}}).start();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(splash.this, mainscreen.class));
                finish();
            }
        }, 1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
