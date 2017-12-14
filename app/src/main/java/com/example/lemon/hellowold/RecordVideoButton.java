package com.example.lemon.hellowold;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Date;

/**
 * Created by zhuoxiuwu on 2017/7/18.
 * email nimdanoob@gmail.com
 */


public class RecordVideoButton extends View {
    private int maxRecordMillionSeconds = 18*1000;
    private int minRecordMillionSeconds;
    private static final int DEFAULT_REACHED_BAR_COLOR = 0Xfffe7780;
    private static final int DEFAULT_COLOR_UNREACHED_COLOR = 0x4cffffff;
    private static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR = 4;
    private float borderSpacing = dip2px(2);
    private RectF outLineArcRectF;

    public Status getStatus() {
        return mStatus;
    }

    private void setStatus(Status status) {
        mStatus = status;
        invalidate();
    }

    public void setCallback(RecordStateCallback callback) {
        this.callback = callback;
    }

    public enum Status{
        End,
        Starting,
        Prepare,
        Preparing
    }

    /**
     *  目前状态
     */
    private Status mStatus = Status.End;

    /**
     * painter of all drawing things
     */
    protected Paint mPaint = new Paint();


    /**
     * height of reached progress bar
     */
    protected float mProgressBarWidth = dip2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);

    protected float mProgressScaleDisstance = dip2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);

    /**
     * color of reached bar
     */
    protected int mReachedBarColor = DEFAULT_REACHED_BAR_COLOR;
    protected int mCenterColor = 0xfffe7780;
    protected float mCenterRadius = dip2px(78);
    /**
     * color of unreached bar
     */
    protected int mUnReachedBarColor = DEFAULT_COLOR_UNREACHED_COLOR;


    /**
     * the length of triangle
     */
    private int triangleLength;

    /**
     * use path to draw triangle
     */
    private Path mPath;

    /**
     * mRadius of view
     */
    private float mRadius = dip2px(35);

    private int progress =0;

    public RecordVideoButton(@NonNull Context context) {
        this(context,null);
    }

    public RecordVideoButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public RecordVideoButton(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        outLineArcRectF = new RectF();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStatus == Status.End){
                    start();
                }else if (mStatus == Status.Starting){
                    stop();
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        float paintWidth = mProgressBarWidth;
//
//        if (heightMode != MeasureSpec.EXACTLY) {
//
//            int exceptHeight = (int) (getPaddingTop() + getPaddingBottom()
//                    + mRadius * 2 + 2*paintWidth);
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight,
//                    MeasureSpec.EXACTLY);
//        }
//
//        if (widthMode != MeasureSpec.EXACTLY) {
//            int exceptWidth = (int) (getPaddingLeft() + getPaddingRight()
//                    + mRadius * 2 + 2*paintWidth);
//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(exceptWidth,
//                    MeasureSpec.EXACTLY);
//        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float canUsedWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - (mProgressBarWidth + mProgressScaleDisstance) * 2;
        float canUseHeight = getMeasuredHeight() - getTop() - getPaddingBottom() - (mProgressBarWidth + mProgressScaleDisstance) * 2;
        float minEdge = canUsedWidth > canUseHeight ? canUseHeight : canUsedWidth;
        mRadius = minEdge / 2;
        centerX = getPaddingLeft() + mProgressBarWidth + mRadius + mProgressScaleDisstance;
        centerY = getPaddingTop() + mProgressBarWidth + mRadius + mProgressScaleDisstance;
        distanceIncrement = mProgressScaleDisstance / PREPEAR_TIME;
    }

    private static final int MESSAGE_START =0x1;
    private static final int MESSAGE_STOP =0x2;
    private static final int MESSAGE_RECORDING =0x3;
    private static final int MESSAGE_COMPLETE =0x4;
    private static final int MESSAGE_PREPARE =0x5;
    private static final int MESSAGE_PREPARING =0x6;

    private static final long PREPEAR_TIME = 200;
    private long prepareTime;
    private long startTime;
    private long currentTime;
    private int mMaxRecordingLength = 18*1000;
    private float currentDistance;
    private float distanceIncrement;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_START:
                    mStatus = Status.Starting;
                    startTime = new Date().getTime();
                    mHandler.sendEmptyMessage(MESSAGE_RECORDING);
                    if (callback!=null){
                        callback.onStart();
                    }
                    break;
                case MESSAGE_STOP:
                    break;
                case MESSAGE_PREPARE:
                    prepareTime = new Date().getTime();
                    mStatus = Status.Prepare;

                    mHandler.sendEmptyMessage(MESSAGE_PREPARING);
                    break;
                case MESSAGE_PREPARING:
                    mStatus = Status.Preparing;
                    long prepearCurrentTime = new Date().getTime();
                    currentDistance = (prepearCurrentTime - prepareTime) * distanceIncrement;
                    invalidate();
                    if (prepearCurrentTime - prepareTime > PREPEAR_TIME) {
                        currentDistance = mProgressScaleDisstance;
                        mHandler.sendEmptyMessage(MESSAGE_START);
                    } else {
                        mHandler.sendEmptyMessageDelayed(MESSAGE_PREPARING, 50);
                    }
                    break;
                case MESSAGE_COMPLETE:
                    mStatus = Status.End;
                    mHandler.removeCallbacksAndMessages(null);
                    if (callback!=null){
                        callback.onStop(true);
                    }
                    startTime = 0;
                    currentTime = 0;
                    invalidate();
                    break;
                case MESSAGE_RECORDING:
                    Message message = Message.obtain();
                    currentTime = new Date().getTime();
                    invalidate();
                    if (currentTime - startTime > mMaxRecordingLength){
                        mHandler.removeCallbacksAndMessages(null);
                        mHandler.sendEmptyMessage(MESSAGE_COMPLETE);
                    }
                    message.what = MESSAGE_RECORDING;
                    mHandler.sendMessageDelayed(message,100);
                    break;
            }
        }
    };

    public void start(){
        mHandler.sendEmptyMessage(MESSAGE_PREPARE);
    }
    public void stop(){
        mHandler.sendEmptyMessage(MESSAGE_COMPLETE);
    }


    private float centerX;
    private float centerY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);

//        canvas.save();
//        canvas.translate(getPaddingLeft(),getPaddingTop());
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mCenterColor);

        canvas.drawCircle(centerX, centerY, mRadius, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
//        //drawUnRead
        mPaint.setColor(mUnReachedBarColor);
        mPaint.setStrokeWidth(mProgressBarWidth);

        float outLineArcLeft = centerX - mRadius - mProgressBarWidth / 2;
        float outLineArcTop = centerY - mRadius - mProgressBarWidth / 2;
        float outLineArcRight = centerX + mRadius + mProgressBarWidth / 2;
        float outLineArcBottom = centerY + mRadius + mProgressBarWidth / 2;

        if (mStatus == Status.Starting || mStatus == Status.Preparing) {
            outLineArcLeft -= currentDistance;
            outLineArcTop -= currentDistance;
            outLineArcRight += currentDistance;
            outLineArcBottom += currentDistance;
        }
        outLineArcRectF.set(outLineArcLeft, outLineArcTop, outLineArcRight, outLineArcBottom);
        canvas.drawArc(outLineArcRectF,-90,360,false,mPaint);
//
////        //draw reached bar
        if (mStatus == Status.Starting) {
            float sweepAngel = getSweepAngel();
            Log.e("record","角度是"+sweepAngel);
            mPaint.setColor(mReachedBarColor);
            mPaint.setStrokeWidth(mProgressBarWidth);
            canvas.drawArc(outLineArcRectF, -90, sweepAngel, false, mPaint);
        }
    }

    public  float dip2px( float dpValue) {

        final float scale = getContext().getResources().getDisplayMetrics().density;
        return  (dpValue * scale );
    }

    private float getSweepAngel(){
        return ((currentTime- startTime)*360f)/maxRecordMillionSeconds;
    }


    public interface RecordStateCallback{
        public void onStart();
        public void onStop(boolean timeout);
    }

    private RecordStateCallback callback;

}
