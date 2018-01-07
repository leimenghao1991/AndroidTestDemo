package com.example.lemon.shortmessage

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.lemon.hellowold.R
import com.example.lemon.hellowold.databinding.ActivityMessageListBinding
import com.example.lemon.shortmessage.utils.requestReadAndSendMsmPermition

class ShortMessageListActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMessageListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_message_list)
        requestReadAndSendMsmPermition(this)
    }
}
