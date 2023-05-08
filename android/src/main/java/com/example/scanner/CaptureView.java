package com.example.scanner;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;



/**
 * description: 扫描框
 */
public final class CaptureView extends View {

    private boolean isFront = true;

    private float widthRatio = 0.9f;//扫码框宽度占view总宽度的比例
    private float heightWidthRatio = 0.5626f;//扫码框的高宽比
    private int leftOffset = -1;//扫码框相对于左边的偏移量，若为负值，则扫码框会水平居中
    private int topOffset = -1;//扫码框相对于顶部的偏移量，若为负值，则扫码框会竖直居中

    private Rect framingRect;//扫码框所占区域

    public CaptureView(Context context) {
        super(context);
    }

    public CaptureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFront(boolean front) {
        isFront = front;
    }

    /**********************************************************************************************/

//    private final DashPathEffect mDashPathEffect = new DashPathEffect(new float[]{20f, 10f}, 0);
    private final int[] laserAlpha = {0, 64, 128, 192, 255, 192, 128, 64};
    private int laserAlphaIndex = 0;

    @Override
    protected void onDraw(Canvas canvas) {

//        final int canvasHeight = canvas.getHeight();
//        final int canvasWidth = canvas.getWidth();
//        Log.e("kalu1", "canvasHeight = " + canvasHeight + ", canvasWidth = " + canvasWidth);


        final Paint mPaint = new Paint();
        mPaint.clearShadowLayer();
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setFakeBoldText(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#cccccc"));
        final DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        final float stroke = 2 * metrics.density;
        mPaint.setStrokeWidth(stroke);

        final int canvasWidth = getHeight();
        final int canvasHeight = getWidth();
        final float layerHeight = canvasHeight * 0.85f;
        final float layerWidth = layerHeight * 1.6f;
        final float layerLeft = (canvasWidth - layerWidth) / 2;
        final float layerTop = (canvasHeight - layerHeight) / 2;
        final float layerRight = layerLeft + layerWidth;
        final float layerBottom = layerTop + layerHeight;


        // step1
//        canvas.drawRect(layerLeft, layerTop, layerRight, layerBottom, mPaint);

        canvas.drawRect(layerBottom, layerLeft, layerTop, layerRight, mPaint);

        drawFace2(canvas, mPaint, layerWidth, layerHeight, layerLeft, layerTop);
    }

    private final void drawFace2(final Canvas canvas, final Paint paint, final float layerWidth, final float layerHeight, final float layerLeft, final float layerTop) {
//        final float faceWidth = layerWidth * 0.3f;
//        final float faceHeight = layerHeight * 0.61f;
//        final float faceLeft = layerLeft + layerWidth * 0.93f - faceWidth;
//        final float faceTop = layerTop + layerHeight * 0.15f;
//        final float faceRight = layerLeft + layerWidth * 0.93f;
//        final float faceBottom = faceTop + faceHeight;

        final float faceWidth = layerWidth * 0.3f;
        final float faceHeight = layerHeight * 0.61f;
        final float faceLeft = layerLeft + layerWidth * 0.43f - faceWidth;
        final float faceTop = layerTop + layerHeight * 0.15f;
        final float faceRight = layerLeft + layerWidth * 0.43f;
        final float faceBottom = faceTop + faceHeight;

        Log.e("IDCard:", "layerWidth:" + layerWidth);
        Log.e("IDCard:", "layerHeight:" + layerHeight);

        Log.e("IDCard:", "faceWidth:" + faceWidth);
        Log.e("IDCard:", "faceHeight:" + faceHeight);

        Log.e("IDCard:", "faceLeft:" + faceLeft);
        Log.e("IDCard:", "faceRight:" + faceRight);
        Log.e("IDCard:", "faceBottom:" + faceBottom);
        Log.e("IDCard:", "faceTop:" + faceTop);

//        E/IDCard: (25934): faceLeft:1924.3439
//        E/IDCard: (25934): faceRight:2364.984
//        E/IDCard: (25934): faceBottom:1044.68
//        E/IDCard: (25934): faceTop:484.7

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawRect(faceTop, faceRight, faceBottom, faceLeft, paint);
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

    private final void drawEmblem(final Canvas canvas, final Paint paint, final float layerWidth, final float layerHeight, final float layerLeft, final float layerTop) {
        final float emblemWidth = layerWidth * 0.19f;
        final float emblemHeight = layerWidth * 0.21f;
        final float emblemTop = layerHeight * 0.08f + layerTop;
        final float emblemLeft = layerHeight * 0.08f + layerLeft;
        final float emblemRight = emblemLeft + emblemWidth;
        final float emblemBottom = emblemTop + emblemHeight;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawRect(emblemLeft, emblemTop, emblemRight, emblemBottom, paint);
    }

    /**********************************************************************************************/

    @Override
    public void setBackground(Drawable background) {
    }

    @Override
    public void setBackgroundColor(int color) {
    }

    @Override
    public void setBackgroundResource(int resid) {
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
    }

    @Override
    public void setBackgroundTintList( ColorStateList tint) {
    }

    @Override
    public void setBackgroundTintMode( PorterDuff.Mode tintMode) {
    }


    /**********************************************************************************************/
}
    
