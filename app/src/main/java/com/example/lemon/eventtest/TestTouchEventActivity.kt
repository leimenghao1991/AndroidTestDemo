package com.example.lemon.eventtest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lemon.hellowold.R
import com.example.lemon.shortmessage.ShortMessageListActivity

class TestTouchEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_touch_event)

        var testView = findViewById(R.id.test_view);
        testView.setOnClickListener {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, ShortMessageListActivity::class.java)
            startActivity(intent)
        }
    }
}
