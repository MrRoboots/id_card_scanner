package com.example.scanner;

import android.app.Activity;

import io.flutter.embedding.engine.plugins.FlutterPlugin;

public class FlutterViewPlugin {

    public static void registerWith(FlutterPlugin.FlutterPluginBinding mFlutterPluginBinding, Activity mActivity) {
        mFlutterPluginBinding.getPlatformViewRegistry().registerViewFactory(Constant.CHANNEL_NAME + "/IDCardScanner", new IDCardScannerPluginFactory(mFlutterPluginBinding.getBinaryMessenger(), mActivity));
    }
}
