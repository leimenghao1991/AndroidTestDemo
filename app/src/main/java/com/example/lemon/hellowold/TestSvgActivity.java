package com.example.lemon.hellowold;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;

/**
 * Created by lemon on 2017/7/27.
 */

public class TestSvgActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_svg);

        final PathView pathView = (PathView) findViewById(R.id.pathview);
        final PathView pathView2 = (PathView) findViewById(R.id.pathview2);
        pathView.useNaturalColors();
        pathView2.useNaturalColors();
        pathView.setFillAfter(true);
        pathView2.setFillAfter(true);
        pathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathView.getPathAnimator().
                        //pathView.getSequentialPathAnimator().
                                delay(100).
                        duration(1500).
                        interpolator(new AccelerateDecelerateInterpolator()).
//                        listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
//                            @Override
//                            public void onAnimationEnd() {
//                                pathView.setVisibility(View.GONE);
//                                pathView2.getPathAnimator().
//                                        //pathView.getSequentialPathAnimator().
//                                                delay(100).
//                                        duration(1500).
//                                        interpolator(new AccelerateDecelerateInterpolator()).
//                                        start();
//                            }
//                        }).
                        start();
            }
        });
    }
}
