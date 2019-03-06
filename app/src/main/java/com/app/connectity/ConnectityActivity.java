package com.app.connectity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.R;

public class ConnectityActivity extends AppCompatActivity {

    private EditText txtNumberConnectivity;
    private EditText txtMessageConnectivity;
    private Button btnSendConnectivity;
    private Button btnWifiConnectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectity);

        txtNumberConnectivity = findViewById(R.id.txt_number_connectivity);
        txtMessageConnectivity = findViewById(R.id.txt_message_connectivity);
        btnSendConnectivity = findViewById(R.id.btn_send_connectivity);
        btnWifiConnectivity = findViewById(R.id.btn_wifi_connectivity);

        final SmsManager manager = SmsManager.getDefault();

        btnSendConnectivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int PERMISSION_REQUEST_CODE = 1;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {

                        Log.d("permission", "permission denied to SEND_SMS - requesting it");
                        String[] permissions = {Manifest.permission.SEND_SMS};

                        requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                    }
                }

                sendSMS("79081050", "demo");

            }
        });

        btnWifiConnectivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                if (!wifi.isWifiEnabled())
                    wifi.setWifiEnabled(true);
                Toast.makeText(ConnectityActivity.this, "Wifi active avec succes", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void sendSMS(String phoneNo, String msg) {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message send", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "ex.getMessage.toString()", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }

}
