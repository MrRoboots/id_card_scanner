package com.example.scanner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.scanner.lib.IViewFinder;


class IDCardViewFinder extends View implements IViewFinder {
    private Rect framingRect;//扫码框所占区域
    private int maskColor = 0x60000000;// 阴影颜色
    private int borderColor = 0xff008577;// 边框颜色
    private int borderStrokeWidth = 12;// 边框宽度

    private Paint maskPaint;// 阴影遮盖画笔
    private Paint borderPaint;// 边框画笔

    private Paint mPaint;

    private float stroke;

    public IDCardViewFinder(Context context) {
        super(context);
        setWillNotDraw(false);//需要进行绘制
        maskPaint = new Paint();
        maskPaint.setColor(maskColor);
        borderPaint = new Paint();
        borderPaint.setColor(borderColor);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderStrokeWidth);
        borderPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
        updateFramingRect();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (getFramingRect() == null) {
            return;
        }
//        initPaint();
//        drawViewFinderMask(canvas);
//        drawViewFinderBorder(canvas);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.clearShadowLayer();
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setFakeBoldText(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#cccccc"));
        final DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        stroke = 2 * metrics.density;
        mPaint.setStrokeWidth(stroke);
    }

    /**
     * 绘制扫码框四周的阴影遮罩
     */
    private void drawViewFinderMask(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Rect framingRect = getFramingRect();
        canvas.drawRect(0, 0, width, framingRect.top, maskPaint);//扫码框顶部阴影
        canvas.drawRect(0, framingRect.top, framingRect.left, framingRect.bottom, maskPaint);//扫码框左边阴影
        canvas.drawRect(framingRect.right, framingRect.top, width, framingRect.bottom, maskPaint);//扫码框右边阴影
        canvas.drawRect(0, framingRect.bottom, width, height, maskPaint);//扫码框底部阴影
    }

    /**
     * 绘制扫码框的边框
     */
    private void drawViewFinderBorder(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        Rect framingRect = getFramingRect();

        int layerLeft = framingRect.left;
        int layerRight = framingRect.right;
        int layerTop = framingRect.top;
        int layerBottom = framingRect.bottom;
        final float layerHeight = canvasHeight * 0.85f;
        final float layerWidth = layerHeight * 1.6f;

        // step1
        canvas.drawRect(layerLeft, layerTop, layerRight, layerBottom, mPaint);
        // step2
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#66666666"));
        canvas.drawRect(0, 0, layerLeft - stroke / 2, canvasHeight, mPaint);
        canvas.drawRect(layerRight + stroke / 2, 0, canvasWidth, canvasHeight, mPaint);
        // step3
        canvas.drawRect(layerLeft - stroke / 2, 0, layerRight + stroke / 2, layerTop - stroke / 2, mPaint);
        canvas.drawRect(layerLeft - stroke / 2, layerBottom + stroke / 2, layerRight + stroke / 2, canvasHeight, mPaint);

        drawFace(canvas, mPaint, layerWidth, layerHeight, layerLeft, layerTop);
    }

    private final void drawFace(final Canvas canvas, final Paint paint, final float layerWidth, final float layerHeight, final float layerLeft, final float layerTop) {
        final float faceWidth = layerWidth * 0.3f;
        final float faceHeight = layerHeight * 0.61f;
        final float faceLeft = layerLeft + layerWidth * 0.93f - faceWidth;
        final float faceTop = layerTop + layerHeight * 0.15f;
        final float faceRight = layerLeft + layerWidth * 0.93f;
        final float faceBottom = faceTop + faceHeight;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawRect(faceLeft, faceTop, faceRight, faceBottom, paint);
    }

    /**
     * 设置framingRect的值（扫码框所占的区域）
     */
    private synchronized void updateFramingRect() {
        final int canvasHeight = getHeight();
        final int canvasWidth = getWidth();
        final float layerHeight = canvasHeight * 0.85f;
        final float layerWidth = layerHeight * 1.6f;
        final float layerLeft = (canvasWidth - layerWidth) / 2;
        final float layerTop = (canvasHeight - layerHeight) / 2;
        final float layerRight = layerLeft + layerWidth;
        final float layerBottom = layerTop + layerHeight;
        framingRect = new Rect((int) layerLeft, (int) layerTop, (int) layerRight, (int) layerBottom);
    }

    @Override
    public Rect getFramingRect() {
        return framingRect;
    }
}
