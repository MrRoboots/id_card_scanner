package com.example.scanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;



import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * ScannerPlugin
 */
public class ScannerPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {

    private MethodChannel channel;

    private Context context;

    private FlutterPluginBinding mFlutterPluginBinding;

    private Activity mActivity;

    private FlutterEventPlugin flutterEventPlugin = new FlutterEventPlugin();

    @Override
    public void onAttachedToEngine(FlutterPluginBinding flutterPluginBinding) {
        context = flutterPluginBinding.getApplicationContext();
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "scanner");
        channel.setMethodCallHandler(this);
        mFlutterPluginBinding = flutterPluginBinding;
        flutterEventPlugin.onAttachedToEngine(flutterPluginBinding);
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("openIdCardScanner")) {
            Intent intent = new Intent(mActivity, ScannerActivity.class);
            mActivity.startActivity(intent);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(FlutterPluginBinding binding) {
//        channel.setMethodCallHandler(null);
//        flutterEventPlugin.onDetachedFromEngine();
    }

    @Override
    public void onAttachedToActivity(ActivityPluginBinding binding) {
        mActivity = binding.getActivity();
        FlutterViewPlugin.registerWith(mFlutterPluginBinding, mActivity);

    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        mActivity = null;
    }

    @Override
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
        mActivity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivity() {
        mActivity = null;
    }
}
