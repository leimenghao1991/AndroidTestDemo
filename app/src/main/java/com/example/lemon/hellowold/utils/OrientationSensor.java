package com.example.lemon.hellowold.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.view.OrientationEventListener;

/**
 * Created by lemon on 2017/10/24.
 */

/**
 * 重力感应器,用来判断当前屏幕应该处于什么状态（横屏还是竖屏），注：这是重力感应器，按照自己的逻辑判断当前应该是什么状态；而不是屏幕状态切换的listener。也就是说
 * {@link OrientationChangedListener}并不是真正的屏幕切换后才会回调，而是告诉开发者，这时候按照一定的逻辑，你可以切换横竖屏了<p>
 * 正常情况下的竖屏是0度，activity获取道的orientation状态是{@link android.content.pm.ActivityInfo#SCREEN_ORIENTATION_PORTRAIT}；<p>
 * 手机顺时针旋转变横屏是90度，activity获取道的orientation状态是{@link android.content.pm.ActivityInfo#SCREEN_ORIENTATION_REVERSE_LANDSCAPE}；<p>
 * 手机逆时针旋转90变横屏是270度，activity获取道的orientation状态是{@link android.content.pm.ActivityInfo#SCREEN_ORIENTATION_LANDSCAPE}；<p>
 * 理想情况下，可以以每个状态的中间角度为临界点，判断进入下一个状态，即：<p>
 * (315, 360)&(0， 45)为竖屏{@link android.content.pm.ActivityInfo#SCREEN_ORIENTATION_PORTRAIT};<p>
 * (45, 180)为横屏{@link android.content.pm.ActivityInfo#SCREEN_ORIENTATION_REVERSE_LANDSCAPE};<p>
 * (180, 315)为横屏{@link android.content.pm.ActivityInfo#SCREEN_ORIENTATION_LANDSCAPE};<p>
 *
 * 但是这种判断太过灵敏，因此加了一个延迟角度{@link #delayDegree}，表示超过了分界线后还需要旋转delayDegree后才会发生屏幕状态切换。
 * 因此每个当前状态切换到下一个状态的旋转范围为：(lowerDegree - delayDegree, biggerDegree + delayDegree)
 *
 * 额，由于加入delayDegree机制计算起来比较麻烦（虽然逻辑比较简单，但是代码写起来很恶心），留给后面需要的人实现吧。。。
 * */
public class OrientationSensor extends OrientationEventListener {
    /**当前手机的旋转角度*/
    private int currentRotation = 0;

    /**延迟角度*/
    private int delayDegree = 15;

    private Activity activity;

    private OrientationChangedListener listener;

    public OrientationSensor(Activity activity) {
        this(activity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public OrientationSensor(Activity activity, int rate) {
        super(activity, rate);
        this.activity = activity;
    }

    public void setListener(OrientationChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onOrientationChanged(int rotation) {
        currentRotation = rotation;
        int currentOrientation = getCurrentOrientation();
        int orientation = caculateOrientation(currentOrientation, rotation);
        if (orientation != currentOrientation) {
            if (listener != null) {
                listener.onOrientationChanged(currentOrientation, orientation);
            }
        }
    }

    /**如果当前是SCREEN_ORIENTATION_LANDSCAPE状态，判断角度改变需要变成什么状态*/
    private int changeLandscapeTo(int rotation){
        /*(180-delayDegree, 315 + delayDegree) 如果当前是SCREEN_ORIENTATION_LANDSCAPE状态，那么为了降低重力感应的灵敏度，那么需要扩大旋转角度*/
        if (rotation > 180 - delayDegree && rotation < 315 + delayDegree) {
            return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }
        /*[315 + delayDegree, 360]&& [0, 45] 竖屏*/
        else if ((rotation >= 315 + delayDegree) && rotation <= 360 || rotation > 0 && rotation < 45) {
            return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
        /*(45, 180 - delayDegree]横屏*/
        else {
            return ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
        }
    }

    /**如果当前是SCREEN_ORIENTATION_REVERSE_LANDSCAPE状态，判断角度改变需要变成什么状态*/
    private int changeReversLandscapeTo(int rotation) {
        /*(45 - delayDegree, 180 + delayDegree]横屏*/
        if (45 - delayDegree < rotation && 180 + delayDegree >= rotation) {
            return ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
        }
        /*(180 + delayDegree, 315)*/
        else if (rotation < 315 && rotation > 180 + delayDegree) {
            return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        } else {
            return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
    }

    /**如果当前是SCREEN_ORIENTATION_PORTRAIT状态，判断角度改变需要变成什么状态*/
    private int changePortraitTo(int rotation) {
        /*[315 - delayDegree, 360] && [0, 45 + delayDegree]*/
        if (rotation >= 315 - delayDegree && rotation <= 360 || rotation >= 0 && rotation <= 45 + delayDegree || rotation < 0) {
            return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
        /*(45 + delayDegree, 180)*/
        else if (rotation > 45 + delayDegree && rotation < 180) {
            return ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
        }
        /*other*/
        else {
            return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }
    }

    private int caculateOrientation(int currentOrientation, int rotation) {
        int orientation = currentOrientation;
        if (rotation < 0) {
            return orientation;
        }
        if (currentOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            orientation = changeLandscapeTo(rotation);
        } else if (currentOrientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
            orientation = changeReversLandscapeTo(rotation);
        } else {
            orientation = changePortraitTo(rotation);
        }
        return orientation;
    }

    public int getCurrentOrientation() {
        return activity.getRequestedOrientation();
    }

    /**主动切换横竖屏的时候，计算竖屏应该切换成哪种横屏模式，或者横屏直接切换成竖屏*/
    public int getPreferOrientation() {
        int currentOrientation = getCurrentOrientation();
        if (currentOrientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE || currentOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        } else {
            if (currentRotation > 0 && currentRotation <= 180) {
                return ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
            }
            if (currentRotation < 360 && currentRotation > 180 || currentRotation <= 0) {
                return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            }
        }
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    public boolean isLandscape(int orientation) {
        boolean isLandscape = orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE || orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
        return isLandscape;
    }

    public interface OrientationChangedListener {
        void onOrientationChanged(int lastOrientation, int newOrientation);
    }
}
