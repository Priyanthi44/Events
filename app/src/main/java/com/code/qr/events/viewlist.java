package com.code.qr.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class viewlist extends ActionBarActivity implements AdapterView.OnItemClickListener {

 private  static customadapter c;
    static LinearLayout lView;

    @Override
    protected void onPause() {
        super.onPause();
        c=null;

        lView.removeAllViewsInLayout();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewlist);
        if(c !=null)
        c=null;
        if(lView!=null)
        lView.removeAllViewsInLayout();

        c = new customadapter(this);
//       Schedule[] fields = Folders.list;
//    		   SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//    		   R.layout.contact,
//    		   cursor,
//    		   fields,
//    		   new int[] {R.id.name});
//    		   // get the listview
//    		   ListView contactlist = (ListView) findViewById(R.id.contactlist);
//    		   // set the adapter and let it render
//    		   contactlist.setAdapter(adapter);
     lView = (LinearLayout)findViewById(R.id.contactlist);


        btnback =(ImageButton)findViewById(R.id.back);
        btnhome =(ImageButton)findViewById(R.id.home);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewlist.this, mainscreen.class));
                finish();
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewlist.this, reminder.class));
                finish();
            }
        });
        setView();

    }
    ImageButton btnback,btnhome;


public static void  setView(){
    for(int i=0;i<c.getCount();i++) {
        try {
            View v = c.getView(i, null, null);
            lView.addView(v);
        }catch(Exception c){
            c.printStackTrace();
        }
    }
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


static int pos ;
    @Override
    public void onItemClick(AdapterView<?> parent, View view,  final int position, long id) {

      pos=position;
    }
}
