package com.example.lemon.hellowold;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lemon on 2017/10/18.
 *
 * 以区域中心点开始，绘制同心环
 *
 * app:rv_ripperCount="2" 同心环个数
 * app:rv_startColor="#0099CC" 同心环最开始的渐变色
 * app:rv_startRadius="64dp"  第一个同心环距离中心点的距离
 *
 * <com.example.lemon.hellowold.RipperView
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         app:rv_ripperCount="2"
         app:rv_startColor="#0099CC"
         app:rv_startRadius="64dp"/>
 */

public class RipperView extends View {
    private int startColor;
    private int ripperCount;
    private int ripperStartRadius;

    private int maxRipperRadius;
    /*同心环的半径*/
    private RectF[] ripperRect;
    /*同心环的颜色透明度*/
    private int[] rippersAlpha;
    /*同心环的宽度*/
    private int rippersWidth;

    private int centerX;
    private int centerY;

    private Paint paint;

    public RipperView(Context context) {
        this(context, null);
    }

    public RipperView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RipperView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray arrays = context.obtainStyledAttributes(attrs, R.styleable.RipperView);

        startColor = getColor(arrays, R.styleable.RipperView_rv_startColor);
        ripperCount = arrays.getInt(R.styleable.RipperView_rv_ripperCount, 0);
        ripperStartRadius = (int) arrays.getDimension(R.styleable.RipperView_rv_startRadius, 0);

        arrays.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private int getColor(TypedArray arrays, int paraId) {
        int color = arrays.getColor(paraId, -1);
        return color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (ripperCount == 0) return;
        prepareData();
        paint.setColor(startColor);
        paint.setStrokeWidth(rippersWidth);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < ripperCount; i++) {
            paint.setAlpha(rippersAlpha[i]);
            canvas.drawArc(ripperRect[i], 0, 360, false, paint);
        }
    }

    private void prepareData() {
        if (maxRipperRadius > 0) return;
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        maxRipperRadius = height > width ? width / 2 : height / 2;
        ripperRect = new RectF[ripperCount];
        rippersAlpha = new int[ripperCount];
        rippersWidth = (maxRipperRadius - ripperStartRadius) / ripperCount;

        int descendAlpha = 0xff / (ripperCount + 1);

        centerX = width / 2;
        centerY = height / 2;
        for (int i = 0; i < ripperCount; i++) {
//            ripperRect[i] = rippersWidth / 2 + rippersWidth * i + ripperStartRadius;
            int distanceToCenter = rippersWidth / 2 + rippersWidth * i + ripperStartRadius;
            RectF tempRect = new RectF();
            tempRect.left = centerX - distanceToCenter;
            tempRect.right = centerX + distanceToCenter;
            tempRect.top = centerY - distanceToCenter;
            tempRect.bottom = centerY + distanceToCenter;
            ripperRect[i] = tempRect;
            rippersAlpha[i] = 0xff - descendAlpha * (1 + i);
        }

    }
}
