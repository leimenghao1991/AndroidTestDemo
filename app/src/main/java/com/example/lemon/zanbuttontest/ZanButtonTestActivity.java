package com.example.lemon.zanbuttontest;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.example.lemon.hellowold.R;

/**
 * Created by lemon on 2017/12/11.
 */

public class ZanButtonTestActivity extends Activity {

    AnimatorSet set;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zan_button_test);

        final View zanIcon = findViewById(R.id.btn_zan);
        final View animView = findViewById(R.id.view_anim);
//        showAnimation(zanIcon);

        set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.zan_button_animator2);
        set.setInterpolator(new SpringScaleInterpolator(0.75f));

        zanIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnimator(animView);
            }
        });
    }

    private void showAnimation(View view) {
        AnimationSet zanAnimSet = new AnimationSet(false);
        ScaleAnimation scaleAnim1 = new ScaleAnimation(1, 3.5f, 1, 3.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotateAnim1 = new RotateAnimation(0, -25, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        TranslateAnimation transAnim1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -0.6f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -4);
        zanAnimSet.setDuration(5000);
        rotateAnim1.setDuration(5000);
        transAnim1.setDuration(5000);

        zanAnimSet.addAnimation(scaleAnim1);
        zanAnimSet.addAnimation(transAnim1);
        zanAnimSet.addAnimation(rotateAnim1);
        zanAnimSet.setFillAfter(true);

        RotateAnimation rotateAnim2 = new RotateAnimation(-25, 0, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
        rotateAnim2.setStartOffset(5000);
        rotateAnim2.setDuration(4500);
        ScaleAnimation scaleAnim2 = new ScaleAnimation(3.5f, 2, 3.5f, 2, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
        scaleAnim2.setStartOffset(5000);
        scaleAnim2.setDuration(4500);
        zanAnimSet.addAnimation(rotateAnim2);

        view.startAnimation(zanAnimSet);
    }

    private void showAnimator(View view) {
        set.setTarget(view);
        set.start();
    }


}
