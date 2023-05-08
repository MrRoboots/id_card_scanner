package com.example.scanner;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


import com.shouzhong.scanner.Callback;
import com.shouzhong.scanner.Result;
import com.shouzhong.scanner.ScannerView;

import java.util.HashMap;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;

public class IDCardScannerPluginView implements PlatformView, MethodChannel.MethodCallHandler {

    private Vibrator vibrator;

    private HashMap<String, Object> hashMap = new HashMap<>();

    Activity mActivity;

    ScannerView scannerView;

    RelativeLayout relativeLayout;

    MethodChannel mChannel;

    public IDCardScannerPluginView(BinaryMessenger mBinaryMessenger, Activity activity, int viewId, Context context) {
        this.mActivity = activity;

        mChannel = new MethodChannel(mBinaryMessenger, Constant.CHANNEL_NAME + "/id_card_status" + viewId);
        mChannel.setMethodCallHandler(this);

        relativeLayout = (RelativeLayout) LayoutInflater.from(mActivity).inflate(R.layout.scnner_view, null, false);

//        relativeLayout = new RelativeLayout(context);

        scannerView = relativeLayout.findViewById(R.id.sv);
//        scannerView = new ScannerView(context);

        scannerView.setShouldAdjustFocusArea(true);
//        scannerView.setViewFinder(new ViewFinder(context));
        scannerView.setViewFinder(new IDCardViewFinder(context));
        scannerView.setSaveBmp(false);
        scannerView.setEnableIdCard(true);
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
            }
        });

//        relativeLayout.addView(scannerView);


    }

    private void startVibrator() {
        if (vibrator == null) vibrator = (Vibrator) mActivity.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(300);
    }

    @Override
    public View getView() {

        return relativeLayout;
    }

    @Override
    public void dispose() {
        if (vibrator != null) {
            vibrator.cancel();
            vibrator = null;
        }

        if (scannerView != null) {
            scannerView.onPause();
            scannerView = null;
        }

     /*   if (scannerView != null) {
            scannerView = null;
        }*/

    /*    if (relativeLayout != null) {
            relativeLayout = null;
        }*/
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if (call.method.equals("openCream")) {
            if (scannerView != null) scannerView.onResume();
            result.success(true);
        } else if (call.method.equals("closeCream")) {
            if (scannerView != null) scannerView.onPause();
            result.success(true);
        }
    }
}
