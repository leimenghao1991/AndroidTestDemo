package com.example.lemon.eventtest

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.lemon.TOUCH_EVENT_MAP
import com.example.lemon.hellowold.utils.LogUtil

/**
 * Created by lemon on 2017/12/14.
 */
class TouchEventTestView2 : View {
    constructor(context: Context): super(context){}

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {}

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtil.e(TOUCH_EVENT_MAP.get(event?.action))
        var result = super.onTouchEvent(event)
        LogUtil.e(TOUCH_EVENT_MAP.get(event?.action) + " " + result)
        return result
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtil.e(TOUCH_EVENT_MAP.get(event?.action))
        var result = super.dispatchTouchEvent(event)
//        result = event?.action == MotionEvent.ACTION_DOWN
        LogUtil.e(TOUCH_EVENT_MAP.get(event?.action) + " " + result)
        return result
    }
}