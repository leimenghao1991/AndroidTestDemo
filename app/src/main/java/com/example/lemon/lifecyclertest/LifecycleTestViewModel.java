package com.example.lemon.lifecyclertest;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

/**
 * Created by lemon on 2017/11/17.
 */

public class LifecycleTestViewModel extends AndroidViewModel {
    private int number = 0;

    public LifecycleTestViewModel(Application application) {
        super(application);
        Log.e("lemon", "new Lifecyclerviewmodel");
        number = 20;
    }

    public int getNumber() {
        return number;
    }
}
