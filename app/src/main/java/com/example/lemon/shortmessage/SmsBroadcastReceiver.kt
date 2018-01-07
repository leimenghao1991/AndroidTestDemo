package com.example.lemon.shortmessage

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsMessage
import com.example.lemon.hellowold.utils.LogUtil
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by lemon on 2018/1/7.
 */
class SmsBroadcastReceiver() : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (!Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent?.action)) {
            return
        }
        var smsSender = ""
        var smsBody = ""

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                smsSender = smsMessage.originatingAddress
                smsBody += smsMessage.displayMessageBody
            }
        } else {
            val bundle = intent?.extras
            if (bundle == null) return
            val pdus: Array<Object> = bundle["pdus"] as Array<Object>
            if (pdus == null) return
            val smsMessages = ArrayList<SmsMessage>(pdus.size)
            for (i in 0..smsMessages.size) {
                smsMessages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                smsBody += smsMessages[i].displayMessageBody
            }
            smsSender = smsMessages[0].originatingAddress
        }
        LogUtil.e("received message : smsBody = " + smsBody + "; sender = " + smsSender)
    }
}