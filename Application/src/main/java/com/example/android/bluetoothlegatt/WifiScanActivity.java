package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WifiScanActivity extends Activity {

    private WifiManager mWifiManager;
    private List<ScanResult> mScanResult;
    private List<String> mScanResultString;
    private ArrayAdapter<String> mScanResultAdapter;

    private Spinner mspinner;
    private TextView mtextview;

    public final static String MYSSID = "SSID";
    public final static String MYPWD = "PWD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wifi_scan);

        mScanResultString = new ArrayList<String>();
        mWifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mWifiManager.startScan();
        mScanResult = mWifiManager.getScanResults();
        for(ScanResult scanResult : mScanResult)
        {
            mScanResultString.add(scanResult.SSID);
        }
        mScanResultAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mScanResultString);
        mScanResultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mspinner = findViewById(R.id.spinner);
        mspinner.setAdapter(mScanResultAdapter);

        mtextview = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                String myssid = (String)mspinner.getSelectedItem();
                String mypwd =  mtextview.getText().toString();

                Intent intent = new Intent();
                intent.putExtra(MYSSID, myssid);
                intent.putExtra(MYPWD, mypwd);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
