package com.example.lemon.eventtest

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.lemon.utils.TOUCH_EVENT_MAP
import com.example.lemon.hellowold.utils.LogUtil
import com.example.lemon.utils.Loge

/**
 * Created by lemon on 2017/12/14.
 */
class TouchEventTestView : View {

    constructor(context: Context?) : super(context) {}

    constructor(context: Context?, attributeSet: AttributeSet) : super(context, attributeSet) {
        setOnTouchListener({ view: View, event: MotionEvent ->
            Loge(TOUCH_EVENT_MAP.get(event.action))
            var result = super.onTouchEvent(event)
            result = event?.action == MotionEvent.ACTION_DOWN
            Loge(TOUCH_EVENT_MAP.get(event.action) + " " + result)
            return@setOnTouchListener result
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Loge(TOUCH_EVENT_MAP.get(event?.action))
        var result = super.onTouchEvent(event)
        Loge(TOUCH_EVENT_MAP.get(event?.action) + " " + result)
        return result
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Loge(TOUCH_EVENT_MAP.get(event?.action))
        var result = super.dispatchTouchEvent(event)
//        result = event?.action == MotionEvent.ACTION_DOWN
        Loge(TOUCH_EVENT_MAP.get(event?.action) + " " + result)
        return result
    }


}