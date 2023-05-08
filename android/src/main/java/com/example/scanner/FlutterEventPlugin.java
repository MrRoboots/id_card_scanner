package com.example.scanner;


import java.util.HashMap;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;

class FlutterEventPlugin implements EventChannel.StreamHandler {

    private static EventChannel eventChannel = null;
    private static EventChannel.EventSink eventSink = null;

    public static void sendContent(HashMap<String, Object> map) {
        if (eventSink != null) {
            eventSink.success(map);
        }
    }

    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
        eventChannel = new EventChannel(binding.getBinaryMessenger(), "scanner_event");
        eventChannel.setStreamHandler(this);
    }

    public void onDetachedFromEngine() {
        if (eventChannel != null) {
            eventChannel.setStreamHandler(null);
            eventChannel = null;
        }
    }

    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
        eventSink = events;
    }

    @Override
    public void onCancel(Object arguments) {
        eventSink = null;
    }
}
