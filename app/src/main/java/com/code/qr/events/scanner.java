package com.code.qr.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Priyanthi on 08/01/2015.
 */
public class scanner extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        ImageButton btnback,btnhome;

        btnback =(ImageButton)findViewById(R.id.back);
        btnhome =(ImageButton)findViewById(R.id.home);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(scanner.this,  mainscreen.class));
                finish();
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(scanner.this, reminder.class));
                finish();
            }
        });
        scanBarcode();
    }



    public void scanBarcode() {
        new IntentIntegrator(this).initiateScan();
    }

    public void scanBarcodeCustomLayout(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureLayout(R.layout.custom_capture_layout);
        integrator.setLegacyCaptureLayout(R.layout.custom_legacy_capture_layout);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.autoWide();
        integrator.initiateScan();
    }

    public void encodeBarcode(View view) {
        new IntentIntegrator(this).shareText("Test Barcode");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
               // Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent scan =         new Intent(scanner.this, result.class);
                scan.putExtra("Result", result.getContents());
                startActivity(scan);

                finish();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    /**
     * Sample of scanning from a Fragment
     */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            startActivity(new Intent(scanner.this, mainscreen.class));
            finish();
            return true;
        }
        if (id == R.id.action_back) {
            startActivity(new Intent(scanner.this, reminder.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }

