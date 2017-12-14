package com.example.lemon.hellowold;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by lemon on 2017/7/26.
 */

public class TestListActivity extends Activity {
    private ListView listView;
    private ListViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);

        initListView();
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ListViewAdapter(TestListActivity.this);
        listView.setAdapter(adapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                Log.e("lemon", "scroll statu" + i);
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                Log.e("lemon", "onscroll");
            }
        });
    }
}
