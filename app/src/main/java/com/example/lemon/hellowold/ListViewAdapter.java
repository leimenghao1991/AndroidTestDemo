package com.example.lemon.hellowold;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by lemon on 2017/7/26.
 */

public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    public ListViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_list_view, viewGroup, false);

        final TextView textView = (TextView) view.findViewById(R.id.position_tv);
        Button button = (Button) view.findViewById(R.id.button);

        textView.setText(String.valueOf(i));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textView.getVisibility() == View.VISIBLE) {
                    textView.setVisibility(View.GONE);
                } else {
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }

}
