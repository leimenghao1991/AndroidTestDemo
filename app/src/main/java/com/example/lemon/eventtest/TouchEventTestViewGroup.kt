package com.example.lemon.eventtest

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.lemon.TOUCH_EVENT_MAP
import com.example.lemon.hellowold.R
import com.example.lemon.hellowold.utils.LogUtil

/**
 * Created by lemon on 2017/12/14.
 */
class TouchEventTestViewGroup : RelativeLayout {

    var mSurfaceColor: Int = Color.WHITE

    constructor(context: Context?) : super(context) {}

    constructor(context: Context?, attrs: AttributeSet, defaultStyleAttr: Int) : super(context, attrs, defaultStyleAttr) {}

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        initParams(context, attrs)
        applyParams();
    }

    private fun applyParams() {
        setBackgroundColor(mSurfaceColor)
    }

    private fun initParams(context: Context?, attrs: AttributeSet) {
        if (context == null) return
        var arrays = context?.obtainStyledAttributes(attrs, R.styleable.TouchEventTestViewGroup)
        mSurfaceColor = arrays.getColor(R.styleable.TouchEventTestViewGroup_tevg_surfaceColor, mSurfaceColor);
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtil.e(TOUCH_EVENT_MAP.get(ev?.action))
        var result = super.dispatchTouchEvent(ev)
        LogUtil.e(TOUCH_EVENT_MAP.get(ev?.action) + " " + result)
        return result
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtil.e(TOUCH_EVENT_MAP.get(ev?.action))
        var result = super.onInterceptTouchEvent(ev)
        LogUtil.e(TOUCH_EVENT_MAP.get(ev?.action) + " " + result)
        return result
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtil.e(TOUCH_EVENT_MAP.get(event?.action))
        var result = super.onTouchEvent(event)
        LogUtil.e(TOUCH_EVENT_MAP.get(event?.action) + " " + result)
        return result
    }
}