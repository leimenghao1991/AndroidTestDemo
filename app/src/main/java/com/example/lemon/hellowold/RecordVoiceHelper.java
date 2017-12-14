package com.example.lemon.hellowold;

import android.view.MotionEvent;
import android.view.View;

import com.skyfishjy.library.RippleBackground;

/**
 * Created by lemon on 2017/10/17.
 */

public class RecordVoiceHelper implements View.OnTouchListener {
    protected View recordBtn;
    protected RippleBackground btnRippleBg;
    private RecordVoiceListener listener;

    private int recordBtnRight;
    private int recordBtnBottom;

    /**用户手指是否在录制按钮范围内*/
    private boolean isFingerInRange = false;

    public RecordVoiceHelper(View recordBtn, RippleBackground btnBg) {
        this.recordBtn = recordBtn;
        this.btnRippleBg = btnBg;

        this.recordBtn.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (listener == null) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startRecord();
                isFingerInRange = isFingerInRange((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (recordBtnRight == 0) {
                    recordBtnRight = recordBtn.getMeasuredWidth();
                    recordBtnBottom = recordBtn.getMeasuredHeight();
                }
                /*判断手指移动范围有没有改变*/
                if (!isFingerRangeChanged(x, y)) {
                    break;
                }
                if (isFingerInRange) {
                    listener.onFingerInRange(v);
                } else {
                    listener.onFingerOutRange(v);
                }
                break;
            case MotionEvent.ACTION_UP:
                endRecord();
        }
        return true;
    }

    private void endRecord() {
        btnRippleBg.stopRippleAnimation();
        listener.onEndRecord(recordBtn, isFingerInRange);
    }

    private void startRecord() {
        btnRippleBg.startRippleAnimation();
        listener.onStartRecord(recordBtn);
    }

    public boolean isFingerInRange(int x, int y) {
        /*判断这次手指是否在录制范围内*/
        boolean inRange = x >= 0 && y >= 0 && x <= recordBtnRight && y <= recordBtnBottom;
        return inRange;
    }

    public boolean isFingerRangeChanged(int x, int y) {
        boolean inRange = isFingerInRange(x, y);
        /*将上一次手指是否在范围内的标记缓存*/
        boolean lastInRange = isFingerInRange;
        /*将这次判断结果缓存起来，供下次使用*/
        isFingerInRange = inRange;
        return inRange != lastInRange;
    }

    public void setListener(RecordVoiceListener listener) {
        this.listener = listener;
    }

    public interface RecordVoiceListener {
        /**点击了录制按钮*/
        void onStartRecord(View v);
        /**手指移动并且离开了录制按钮范围（但是还是按下状态）*/
        void onFingerOutRange(View v);
        /**手指移动并且进入了录制按钮范围*/
        void onFingerInRange(View v);
        /**结束录制，cancel表示是否取消录制*/
        void onEndRecord(View v, boolean cancel);
    }
}
