package com.example.lemon.shortmessage.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.example.lemon.app.MyApplication
import com.example.lemon.hellowold.utils.LogUtil

/**
 * Created by lemon on 2018/1/7.
 */

/**检测是否有读取短信的权限*/
fun isSmsPermissionGranted(): Boolean {
    val context = MyApplication.context
    return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
}

fun requestReadAndSendMsmPermition(activity: Activity) {
    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_SMS)) {
        LogUtil.e("require permission")
    }
    val smsPermission = arrayOf(Manifest.permission.READ_SMS)
    ActivityCompat.requestPermissions(activity, smsPermission, 20)
}