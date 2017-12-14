package com.example.lemon.EmptyFragmentTest;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by lemon on 2017/11/24.
 */

public class TestFragment extends Fragment {
    private IListner mListner;

    public void setListner(IListner listner) {
        mListner = listner;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mListner != null) {
            mListner.onResult();
        }
    }

    public interface IListner {
        void onResult();
    }
}
