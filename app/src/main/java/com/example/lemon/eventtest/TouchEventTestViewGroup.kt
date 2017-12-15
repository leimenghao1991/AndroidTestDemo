package com.example.lemon.eventtest

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.example.lemon.utils.TOUCH_EVENT_MAP
import com.example.lemon.hellowold.R
import com.example.lemon.hellowold.utils.LogUtil
import com.example.lemon.utils.Loge

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
        Loge(TOUCH_EVENT_MAP.get(ev?.action))
        var result = super.dispatchTouchEvent(ev)
        Loge(TOUCH_EVENT_MAP.get(ev?.action) + " " + result)
        return result
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Loge(TOUCH_EVENT_MAP.get(ev?.action))
        var result = super.onInterceptTouchEvent(ev)
        result = ev?.action == MotionEvent.ACTION_MOVE
        Loge(TOUCH_EVENT_MAP.get(ev?.action) + " " + result)
        return result
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Loge(TOUCH_EVENT_MAP.get(event?.action))
        var result = super.onTouchEvent(event)
        Loge(TOUCH_EVENT_MAP.get(event?.action) + " " + true)
        return true
    }
}