package com.code.qr.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.code.qr.events.process.Folders;


public class reminder extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        (  findViewById(R.id.btnscanqrcode)).setOnClickListener(onClickListener);
        if(Folders.list!=null) {
            (findViewById(R.id.btnviewlist)).setOnClickListener(onClickListener);
        }else{
           (findViewById(R.id.btnviewlist)).setEnabled(false);
        }
        ImageButton btnback,btnhome;

        btnback =(ImageButton)findViewById(R.id.back);
        btnhome =(ImageButton)findViewById(R.id.home);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(reminder.this,  mainscreen.class));
                finish();
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(reminder.this, mainscreen.class));
                finish();
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (v.getId() == R.id.btnscanqrcode) {
                Toast.makeText(reminder.this, "Loading...", Toast.LENGTH_LONG).show();
                startActivity(new Intent(reminder.this, scanner.class));
                finish();
            }
            if (v.getId() == R.id.btnviewlist) {
                startActivity(new Intent(reminder.this, viewlist.class));
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
