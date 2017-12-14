package com.example.lemon

import android.view.MotionEvent

/**
 * Created by lemon on 2017/12/14.
 */
public val TOUCH_EVENT_MAP = mapOf<Int, String>(Pair(MotionEvent.ACTION_DOWN, "down")
        , Pair(MotionEvent.ACTION_MOVE, "move")
        , Pair(MotionEvent.ACTION_UP, "up"))