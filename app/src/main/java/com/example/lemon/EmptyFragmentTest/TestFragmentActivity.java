package com.example.lemon.EmptyFragmentTest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lemon.hellowold.R;

/**
 * Created by lemon on 2017/11/24.
 */

public class TestFragmentActivity extends FragmentActivity {
    int i = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        View view = findViewById(R.id.button);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestFragmentActivity.this, TestActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("lemon", "activity onResult");
    }

    public static class TestActivity extends Activity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Toast.makeText(this, " i = " + 2, Toast.LENGTH_SHORT).show();
        }
    }

}
