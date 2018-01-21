package com.example.android.barcodescan;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.androidhive.barcode.BarcodeReader;

public class MainActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    String domain = "localhost:3000";
    String barcodeUrl = domain + "/api/checkIP";

    StringRequest stringRequest;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onScanned(Barcode barcode) {
        Toast.makeText(MainActivity.this, "BarCode Scanned", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, barcodeUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseFinal) {
                        Log.v("Response from server: ", responseFinal);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Error!", error.toString());
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // Extracting information from scanned barcode and sending it to above URL.
                // For the purpose of this project, a raw harcoded IP is used and assumed to be extracted from the scanned barcode.
                params.put("ID", "221.221.12.48");
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        Log.v("onScanError", "Error Scanning BarCode");
        Toast.makeText(MainActivity.this, "Error Scanning BarCode", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraPermissionDenied() {
        Log.v("CameraPermissionDenied", "Camera Permission Denied");
        Toast.makeText(MainActivity.this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
    }
}
