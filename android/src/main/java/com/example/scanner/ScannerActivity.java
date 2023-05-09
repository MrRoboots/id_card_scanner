package com.example.scanner;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentActivity;


import com.example.scanner.lib.Callback;
import com.example.scanner.lib.Result;
import com.example.scanner.lib.ScannerView;

import java.util.HashMap;

public class ScannerActivity extends FragmentActivity {

    private ScannerView scannerView;

    private Vibrator vibrator;

    private HashMap<String, Object> hashMap = new HashMap<>();

    private Button back, input;

    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.scnner_view);

        relativeLayout = findViewById(R.id.rv);

        scannerView = findViewById(R.id.sv);

        back = findViewById(R.id.btn_back);
        input = findViewById(R.id.btn_input);

        scannerView.setShouldAdjustFocusArea(true);
        scannerView.setViewFinder(new IDCardViewFinder(this));
//        scannerView.setViewFinder(new CaptureView(this));
//        scannerView.setViewFinder(new ViewFinder(this));
        scannerView.setSaveBmp(false);
        scannerView.setEnableIdCard(true);
//        scannerView.setEnableIdCard2(true);
        scannerView.setRotateDegree90Recognition(true);
        scannerView.setCallback(new Callback() {
            @Override
            public void result(Result result) {
                System.out.println("识别结果:" + result);
                hashMap.put("type", result.type);
                hashMap.put("data", result.data);
                FlutterEventPlugin.sendContent(hashMap);
                startVibrator();
                scannerView.restartPreviewAfterDelay(2000);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("input", "");
                FlutterEventPlugin.sendContent(hashMap);
                finish();
            }
        });
    }


    @Override
    protected void onResume() {
        Log.e("ScannerActivity", "onResume");
        scannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("ScannerActivity", "onPause");
        super.onPause();
        scannerView.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.e("ScannerActivity", "onDestroy");
        super.onDestroy();
        stop();
    }

    private void stop() {
        if (vibrator != null) {
            vibrator.cancel();
            vibrator = null;
        }
        if (relativeLayout != null) {
            relativeLayout = null;
        }
    }

    private void startVibrator() {
        if (vibrator == null) vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(300);
    }
}