package com.example.lemon.hellowold;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by lemon on 16/12/2.
 */

public class GuaGuaLeView extends View {
    private Random rnd;
    private Paint paint;
    private Paint clearPaint;
    private static final String[] PRIZE = {"恭喜,您中了一等奖,奖金1亿元",
            "恭喜,您中了一等奖,奖金2亿元",
            "恭喜,您中了一等奖,奖金3亿元",
            "恭喜,您中了一等奖,奖金4亿元"
    };
    /**     */
    private static final int FINGER = 50;
    /**   */
    private Bitmap bmpBuffer;
    /**     */
    private Canvas cvsBuffer;
    private int curX, curY;

    public GuaGuaLeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rnd = new Random();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);
        clearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        clearPaint.setStrokeJoin(Paint.Join.ROUND);
        clearPaint.setStrokeCap(Paint.Cap.ROUND);
        clearPaint.setStrokeWidth(FINGER); //
        drawBackground();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//
        bmpBuffer = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cvsBuffer = new Canvas(bmpBuffer);
        cvsBuffer.drawColor(Color.parseColor("#FF808080"));
    }

    /**
     * @return PRIZE
     */
    private int getPrizeIndex() {
        return rnd.nextInt(PRIZE.length);
    }

    /**
     *                    */
    private void drawBackground() {
        Bitmap bmpBackground = BitmapFactory.decodeResource(
                getResources(), R.mipmap.img);
// 么  临”  bmpBackground
        Bitmap bmpBackgroundMutable = bmpBackground.copy(Bitmap.Config.ARGB_8888, true); //
        Canvas cvsBackground = new Canvas(bmpBackgroundMutable);
        //
        String text = PRIZE[getPrizeIndex()];
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        int x = (bmpBackgroundMutable.getWidth() - rect.width()) / 2;
        int y = (bmpBackgroundMutable.getHeight() - rect.height()) / 2;
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
        paint.setShadowLayer(10, 0, 0, Color.GREEN);
        cvsBackground.drawText(text, x, y, paint);
        paint.setShadowLayer(0, 0, 0, Color.YELLOW);
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(new BitmapDrawable(getResources(), bmpBackgroundMutable));
        } else {
            this.setBackgroundDrawable(
                    new BitmapDrawable(bmpBackgroundMutable));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.drawBitmap(bmpBuffer, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                curX = x;
                curY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                cvsBuffer.drawLine(curX, curY, x, y, clearPaint);
                invalidate();
                curX = x;
                curY = y;
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
