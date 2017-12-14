package com.example.lemon.hellowold.orientationchange;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;

import com.example.lemon.hellowold.R;
import com.example.lemon.hellowold.utils.LogUtil;
import com.example.lemon.hellowold.utils.OrientationSensor;

/**
 * Created by lemon on 2017/10/24.
 */

public class OrientationChangeActivity extends Activity {
    private Button button;
    private OrientationSensor mOrientationListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.e();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_change);

        button = (Button) findViewById(R.id.button);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        button.setOnClickListener(new View.OnClickListener() {
            boolean isFullScreen = false;
            @Override
            public void onClick(View v) {
                if (!isFullScreen) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    mOrientationListener.enable();
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    mOrientationListener.disable();
                }
                isFullScreen = !isFullScreen;
            }
        });
        startOrientationChangeListener();

    }

    private void getSensor() {
//        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        manager.getDefaultSensor()
    }

    private final void startOrientationChangeListener() {
        mOrientationListener = new OrientationSensor(this);
        mOrientationListener.setListener(new OrientationSensor.OrientationChangedListener() {
            @Override
            public void onOrientationChanged(int lastOrientation, int newOrientation) {
                if (!mOrientationListener.isLandscape(lastOrientation) || !mOrientationListener.isLandscape(newOrientation)) {
                    return;
                }
                setRequestedOrientation(newOrientation);
            }
        });
    }

    private void changeOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int degree= 90 * rotation;
        float rad = (float)Math.PI / 2 * rotation;
        LogUtil.e("rotation = " + rotation + "; degree = " + degree + "; rad = " + rad);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.e();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.e();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrientationListener.disable();
        LogUtil.e();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.e();
    }
}
