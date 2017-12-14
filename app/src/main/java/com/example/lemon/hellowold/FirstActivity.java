package com.example.lemon.hellowold;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.lemon.hellowold.orientationchange.OrientationChangeActivity;
import com.example.lemon.lifecyclertest.LifecycleTestActivity;

/**
 * Created by lemon on 2017/10/24.
 */

public class FirstActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, LifecycleTestActivity.class);
                startActivity(intent);
            }
        });
    }
}
