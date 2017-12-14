package com.example.lemon.hellowold;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by lemon on 2017/7/28.
 */

public class TestConcernAnimActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_concern_anim);

        testConcernAnim();
    }

    private void testConcernAnim() {
        final ImageView concernIv1 = (ImageView) findViewById(R.id.concern);
        final ImageView concernFinish = (ImageView) findViewById(R.id.concern_finish);

        Button button = (Button) findViewById(R.id.anim_btn);
        final Animation animationSet =  AnimationUtils.loadAnimation(this, R.anim.concern_disapprear_anim);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                concernIv1.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.concern_appear_anim);
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                concernFinish.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation2.setStartOffset(200);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concernIv1.startAnimation(animationSet);
                concernFinish.startAnimation(animation2);
            }
        });
    }
}
