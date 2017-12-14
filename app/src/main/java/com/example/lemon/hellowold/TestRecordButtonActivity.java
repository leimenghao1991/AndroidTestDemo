package com.example.lemon.hellowold;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by lemon on 2017/7/28.
 */

public class TestRecordButtonActivity extends Activity {
    RecordVideoButton recordVideoButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDownloadManager();
        setContentView(R.layout.activity_record_button);
        recordVideoButton = (RecordVideoButton) findViewById(R.id.record_btn);

        recordVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recordVideoButton.start();
            }
        });
    }

    private void initDownloadManager() {
        FileDownloader.setupOnApplicationOnCreate(getApplication());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lemon", "test record button activity destoryed");
    }
}
