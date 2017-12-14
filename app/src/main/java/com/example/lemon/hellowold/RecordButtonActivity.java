package com.example.lemon.hellowold;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.skyfishjy.library.RippleBackground;

/**
 * Created by lemon on 2017/10/16.
 */

public class RecordButtonActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_voice);

        /*final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        ImageView imageView=(ImageView)findViewById(R.id.centerImage);

        RecordVoiceHelper helper = new RecordVoiceHelper(imageView, rippleBackground);
        helper.setListener(new RecordVoiceHelper.RecordVoiceListener() {
            @Override
            public void onStartRecord(View v) {
                Toast.makeText(RecordButtonActivity.this, "开始录制", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFingerOutRange(View v) {
                Toast.makeText(RecordButtonActivity.this, "移除范围了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFingerInRange(View v) {
                Toast.makeText(RecordButtonActivity.this, "移入范围了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEndRecord(View v, boolean cancel) {
                String tint = cancel ? "取消录制" : "结束录制";
                Toast.makeText(RecordButtonActivity.this, tint, Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
