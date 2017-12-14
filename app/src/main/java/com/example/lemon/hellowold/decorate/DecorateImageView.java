package com.example.lemon.hellowold.decorate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lemon on 2017/10/31.
 */

public class DecorateImageView extends android.support.v7.widget.AppCompatImageView {
    public DecorateImageView(Context context) {
        this(context, null);
    }

    public DecorateImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DecorateImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();

    }
}
