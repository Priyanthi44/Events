package com.code.qr.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class choosevent extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosevent);
        (  findViewById(R.id.btnchoose)).setOnClickListener(onClickListener);
         s =(Spinner)(findViewById(R.id.event));
        s.setOnItemSelectedListener(this);
        //TODO use an adapter instead of a string
        ImageButton btnback,btnhome;

        btnback =(ImageButton)findViewById(R.id.back);
        btnhome =(ImageButton)findViewById(R.id.home);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choosevent.this,  mainscreen.class));
                finish();
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choosevent.this, exclusiveevents.class));
                finish();
            }
        });
    }
    Spinner s;
    String str;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (v.getId() == R.id.btnchoose) {
                Intent i = new Intent(choosevent.this, exlusiveevent.class);
                i.putExtra("Event",str);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    str =((TextView)view).getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        str =((TextView)s.getSelectedView()).getText().toString();
    }
}
