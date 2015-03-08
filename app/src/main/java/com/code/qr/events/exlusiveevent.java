package com.code.qr.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class exlusiveevent extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exlusiveevent);
        Intent intent = getIntent();
        ( (TextView)(findViewById(R.id.event))).setText( intent.getStringExtra("Event"));
        ( (Button) findViewById(R.id.btnparty)).setOnClickListener(onClickListener);
        ( (Button) findViewById(R.id.btnafterparty)).setOnClickListener(onClickListener);
        ImageButton btnback,btnhome;

        btnback =(ImageButton)findViewById(R.id.back);
        btnhome =(ImageButton)findViewById(R.id.home);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(exlusiveevent.this,  mainscreen.class));
                finish();
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(exlusiveevent.this, choosevent.class));
                finish();
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (v.getId() == R.id.btnparty) {
                Toast.makeText(exlusiveevent.this, "Loading...", Toast.LENGTH_LONG).show();
                Intent i = new Intent(exlusiveevent.this, afterevent.class);
                i.putExtra("Event",((TextView)(findViewById(R.id.event ))).getText());
                 i.putExtra("caption",((Button)(findViewById(R.id.btnparty ))).getText());
                startActivity(i);
                finish();
            }
            if (v.getId() == R.id.btnafterparty) {
                Toast.makeText(exlusiveevent.this, "Loading...", Toast.LENGTH_LONG).show();
                Intent i = new Intent(exlusiveevent.this, afterevent.class);
                i.putExtra("Event",((TextView)(findViewById(R.id.event ))).getText());
                i.putExtra("caption",((Button)(findViewById(R.id.btnafterparty ))).getText());
                startActivity(i);
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
