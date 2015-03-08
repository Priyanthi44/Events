package com.code.qr.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class exclusiveevents extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclusiveevents);
        (  findViewById(R.id.btnall)).setOnClickListener(onClickListener);
        ( findViewById(R.id.btnchose)).setOnClickListener(onClickListener);
        ImageButton btnback,btnhome;

        btnback =(ImageButton)findViewById(R.id.back);
        btnhome =(ImageButton)findViewById(R.id.home);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(exclusiveevents.this,  mainscreen.class));
                finish();
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(exclusiveevents.this, mainscreen.class));
                finish();
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (v.getId() == R.id.btnall) {

                    Intent i =new Intent(exclusiveevents.this, afterevent.class);
                    i.putExtra("caption","All Offers" );
                    i.putExtra("Event","All Offers" );
                    startActivity(i);
                    finish();
                }
            if (v.getId() == R.id.btnchose) {
                startActivity(new Intent(exclusiveevents.this,choosevent.class));
                finish();
            }
        }
    };



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
