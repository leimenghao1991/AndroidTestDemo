package com.example.lemon.hellowold;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lemon on 2017/10/16.
 */

public class RecordVoiceView extends View implements View.OnTouchListener {
    private RecordVoiceListener listener;

    public RecordVoiceView(Context context) {
        this(context, null);
    }

    public RecordVoiceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecordVoiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startRecordVoice();
                break;
            case MotionEvent.ACTION_MOVE:
                fingerMoved(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                endRecordVoice(event.getX(), event.getY());
        }
        return true;
    }

    private void endRecordVoice(float x, float y) {
        Log.e("lemon", "end x = "+ x + "; end y = " + y);
    }

    private void fingerMoved(float x, float y) {
        Log.e("lemon", "x = "+ x + "; y = " + y);

    }

    private void startRecordVoice() {

    }


    public interface RecordVoiceListener {
        void onStartRecord();
        void onEndRecord(boolean cancel);
    }

}
