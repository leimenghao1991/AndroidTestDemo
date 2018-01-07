package com.example.lemon.app

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.provider.Telephony
import com.example.lemon.hellowold.utils.LogUtil
import com.example.lemon.shortmessage.SmsBroadcastReceiver

/**
 * Created by lemon on 2018/1/7.
 */
class MyApplication : Application() {
//    lateinit var smsBroadcastReceiver: SmsBroadcastReceiver

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        backgroundConfig()
    }

    fun backgroundConfig() {
//        smsBroadcastReceiver = SmsBroadcastReceiver("", "")
//        registerReceiver(smsBroadcastReceiver, IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))
        LogUtil.e("register sms broadcast receiver")
    }

    fun backgroundTerminate() {
//        unregisterReceiver(smsBroadcastReceiver)
        LogUtil.e("unregister sms broadcast receiver")
    }

    override fun onTerminate() {
        backgroundTerminate()
        super.onTerminate()
    }
}