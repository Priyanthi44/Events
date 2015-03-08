package com.code.qr.events;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.code.qr.events.Notification.ScheduleClient;
import com.code.qr.events.process.Folders;
import com.code.qr.events.process.Schedule;
import com.code.qr.events.process.SubString;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class result extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

String[] details ;
    private ScheduleClient scheduleClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String event = intent.getStringExtra("Result");
        details =new String[2];
        details = SubString.getDetails(event) ;
        //TODO
        //Checking for out dated
        Spinner s =(Spinner)findViewById(R.id.days);
        s.setOnItemSelectedListener(this);
        try {
            if( SubString.dateDiff(details[1]) >=1) {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setTitle("Expired Item");
                dlgAlert.setMessage("The item has been already expired");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);

                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                               //starting scanner
                                startActivity(new Intent(result.this, reminder.class));
                                finish();

                            }
                        });
                dlgAlert.show();
            }else if(SubString.dateDiff(details[1]) ==0){
                s.setEnabled(false);
            }
        } catch (Exception vc) {
        }
        //Saving notifications
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();


        ( (TextView)(findViewById(R.id.event))).setText(details[0]);
        ( (TextView)(findViewById(R.id.eventDate))).setText(details[1]);
        (findViewById(R.id.btnproceed)).setOnClickListener(onClickListener);
        ImageButton btnback,btnhome;

        btnback =(ImageButton)findViewById(R.id.back);
        btnhome =(ImageButton)findViewById(R.id.home);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                startActivity(new Intent(result.this,mainscreen.class));
                finish();
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(result.this, scanner.class));
                finish();
            }
        });
    }
    int remindin =1;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (v.getId() == R.id.btnproceed) {

            	//TODO save file in a thread
                String sdate =null;
                try {
                    if (SubString.dateDiff(details[1]) < 0) {
                         sdate = SubString.setReminder(details[1]
                                , remindin);
                    } else
                        sdate =SubString.getStringDate(details[1]);

                }catch(Exception c){}
             final Schedule n =   new Schedule(details[0],details[1],sdate );
                if(Folders.list ==null)
               Folders.list = new ArrayList<String[]>();
                Folders.list.add(details);
                try {
                    onDateSelectedButtonClick(SubString.getDate(sdate));
                }catch(Exception c ){
                    c.printStackTrace();
                }
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                         // Create Folders
                        Schedule.saveReminder(n);


                    }}).start();

            	//TODO add to the list

                startActivity(new Intent(result.this, viewlist.class));
                finish();
            }
        }
    };
    public void onDateSelectedButtonClick( Date picker){
        // Get the date from our datepicker

        int day = picker.getDate();

        int month = picker.getMonth();
        int year = picker.getYear();
        // Create a new calendar set to the date chosen
        // we set the time to midnight (i.e. the first minute of that day)
        Calendar c = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        c.set(year + 1900, month, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
        scheduleClient.setAlarmForNotification(c);
        // Notify the user what they just did
        Toast.makeText(this, "Notification set for: " + day + "/" + (month + 1) + "/" + (year+1900), Toast.LENGTH_SHORT).show();
    }
    public int getDate(String s){
        if(s.equalsIgnoreCase("One Day"))
           return 1 ;
            else  if(s.equalsIgnoreCase("Two Days"))
            return 2;
        else  if(s.equalsIgnoreCase("Three Days"))
            return 3;
        else  if(s.equalsIgnoreCase("Four Days"))
            return 4;
        else  if(s.equalsIgnoreCase("Five Days"))
            return 5;
        else  if(s.equalsIgnoreCase("Six Days"))
            return 6;
        else
            return 7;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            startActivity(new Intent(result.this, mainscreen.class));
            finish();
            return true;
        }
        if (id == R.id.action_back) {
            startActivity(new Intent(result.this, scanner.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView t =(TextView)view;
remindin =getDate(t.getText().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        remindin=1;
    }
}
