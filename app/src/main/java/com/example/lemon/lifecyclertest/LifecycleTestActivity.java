package com.example.lemon.lifecyclertest;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lemon.hellowold.R;
import com.example.lemon.hellowold.databinding.ActivityLifecycleTestBinding;

/**
 * Created by lemon on 2017/11/17.
 */

public class LifecycleTestActivity extends LifecycleActivity {

    private LifecycleTestViewModel mViewModel;
    private ActivityLifecycleTestBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle_test);

        Log.e("lemon", "oncreate");
        initData();
        testStaticClass();
    }

    private void testStaticClass() {
        TestStaticParam param = new TestStaticParam();
        Log.e("lemon", "toString = " + param.getParam().toString());
        param.getParam().age = 10;
        param.getParam().name = "zxw";
        TestStaticParam param1 = new TestStaticParam();
        Log.e("lemon", "toString = " + param.getParam().toString());
    }

    private void initData() {
        mViewModel = ViewModelProviders.of(this).get(LifecycleTestViewModel.class);
        String info = mViewModel.toString() + "    number = " + mViewModel.getNumber();
        mBinding.infoTv.setText(info);
    }

    @Override
    protected void onDestroy() {
        Log.e("lemon", "ondestory");
        super.onDestroy();
    }
}
