package com.example.lemon.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by lemon on 2017/12/15.
 */

var LOG_TAG = "=====>";
var PREFIX_LENGTH = 80

private fun clazzName(traceElement: StackTraceElement): String {
    var className = traceElement.className
    className = className.substring(className.lastIndexOf('.') + 1)
    return className
}

private fun time() : String {
    var date = Date()
    var sdf = SimpleDateFormat("yyyy-mm-dd HH:mm:ss.SSS")
    return sdf.format(date)
}

private fun linkMethod(traceElement: StackTraceElement): String{
    return traceElement.methodName
}

private fun lineNum(traceElement: StackTraceElement): String {
    return traceElement.lineNumber.toString()
}

private fun logPrefix() : String{
    var traceElement = Exception().stackTrace[2]
    return "[" + clazzName(traceElement) + "." + linkMethod(traceElement) + "(" + lineNum(traceElement) + ")]: "
}

public fun Loge(message: String?) {
    var prefix = logPrefix()
    if (prefix.length < PREFIX_LENGTH) {
        var holderStrLength = PREFIX_LENGTH - prefix.length;
        if (holderStrLength > 0) {
            prefix = String.format("%" + holderStrLength + "s", "") + prefix
        }
    }
    Log.e(LOG_TAG, prefix + message)
}


