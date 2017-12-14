package com.example.lemon.hellowold.decorate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lemon.hellowold.R;

/**
 * Created by lemon on 2017/10/31.
 */

public class DecorateActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorate);
    }
}
