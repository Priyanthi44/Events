package com.code.qr.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;


public class mainscreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        (  findViewById(R.id.btnreminder)).setOnClickListener(onClickListener);
        ( findViewById(R.id.btnexevent)).setOnClickListener(onClickListener);
        ( findViewById(R.id.btnaisle)).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (v.getId() == R.id.btnreminder) {
                startActivity(new Intent(mainscreen.this, reminder.class));
                finish();
            }
            if (v.getId() == R.id.btnexevent) {
                startActivity(new Intent(mainscreen.this, exclusiveevents.class));
                finish();
            }
            if (v.getId() == R.id.btnaisle) {
                Intent i =new Intent(mainscreen.this, afterevent.class);
                i.putExtra("caption","Aisle Finder" );
                i.putExtra("Event","Aisle Finder" );
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
