package com.example.scanner;

import android.graphics.Rect;

import com.example.scanner.lib.IViewFinder;


class ViewFinder2 implements IViewFinder {
    @Override
    public Rect getFramingRect() {
        return new Rect(240, 240, 840, 840);
    }
}
