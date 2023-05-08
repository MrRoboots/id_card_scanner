package com.example.scanner;

import android.app.Activity;
import android.content.Context;


import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class IDCardScannerPluginFactory extends PlatformViewFactory {
    private final BinaryMessenger mBinaryMessenger;
    private final Activity mActivity;

    public IDCardScannerPluginFactory(BinaryMessenger binaryMessenger, Activity activity) {
        super(StandardMessageCodec.INSTANCE);
        this.mBinaryMessenger = binaryMessenger;
        this.mActivity = activity;
    }

    @Override
    public PlatformView create(Context context, int viewId,  Object args) {
        IDCardScannerPluginView idCardScannerPluginView = null;
        try {
            idCardScannerPluginView = new IDCardScannerPluginView(mBinaryMessenger, mActivity, viewId, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert idCardScannerPluginView != null;
        return idCardScannerPluginView;
    }
}
