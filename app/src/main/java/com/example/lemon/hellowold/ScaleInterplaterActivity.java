package com.example.lemon.hellowold;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

/**
 * Created by lemon on 2017/7/28.
 */

public class ScaleInterplaterActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_concern_anim);

        testScaleInterplate();
    }

    private void testScaleInterplate() {
        final View concernLayout = findViewById(R.id.concern_layout);
        Button button = (Button) findViewById(R.id.anim_btn);

        final Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        BounceInterpolator interpolator = new BounceInterpolator();
        Interpolator myInterpolator = new Interpolator() {
            @Override
            public float getInterpolation(float v) {
                return 0;
            }
        };
        scaleAnim.setInterpolator(new BounceInterpolator());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concernLayout.startAnimation(scaleAnim);
            }
        });
    }

    public class MyInterplator extends CycleInterpolator {
        /*正弦函数的高度衰减点 attenuationStartPosition >= 0 && attenuationStartPosition <= 1*/
        private float attenuationStartPosition = 0;
        private float scale;
        /*衰减率*/
        private float attenuationRate;
        /*衰减后坐标增长量*/
        private float releativeXRatio;

        public MyInterplator(float cycles, float attenuationStartPosition, float scale) {
            super(cycles);
            this.attenuationStartPosition = attenuationStartPosition;
            this.scale = scale;
            attenuationRate = scale / (1 - attenuationStartPosition);
            releativeXRatio = 1 / (1 - attenuationStartPosition);
        }

        @Override
        public float getInterpolation(float input) {
            float value = 0;
            if (input <= attenuationStartPosition) {
                value = (float)(Math.cos((input + 2 - attenuationStartPosition) * Math.PI) / 2.0f) + 0.5f;
            } else {
                float decrement = attenuationRate * (input - attenuationStartPosition);
                float releativeInput = releativeXRatio * (input - attenuationStartPosition);
                value = (scale - decrement) * super.getInterpolation(releativeInput) + 1;
            }
            return value;
        }
    }
}
