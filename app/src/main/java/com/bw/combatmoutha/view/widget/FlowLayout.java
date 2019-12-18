package com.bw.combatmoutha.view.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FlowLayout extends ViewGroup {
    //屏幕的宽度
    int screenWid;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenWid = displayMetrics.widthPixels;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int wSpace = 30;
        int hSpace = 20;

        int left = wSpace;
        int top = hSpace;
        int right = 0;
        int bottom = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            //测量孩子的宽和高
            // TODO: 2019/12/17 必须用child 去测量和获取宽高 ，不能直接调用 measure 和 getMeasuredWidth
            childAt.measure(0, 0);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();

            right = left + measuredWidth;
            if (right < screenWid) {
                bottom = top + measuredHeight;
            } else {
                //右侧超出屏幕宽度，要换行， 从下一行的最左侧开始摆放
                left = 0 + wSpace;
                //换行之后，你的头到别人的脚下
                top = bottom + hSpace;

                //右下无脑算
                right = left + measuredWidth;
                bottom = top + measuredHeight;
            }
            //摆放孩子    childAt.layout
            childAt.layout(left, top, right, bottom);
            // TODO: 2019/12/17 帮下一个孩子计算出 left 和 top
            left = right + wSpace;
        }
    }

    public void addTag(final String tag) {
        final TextView textView = new TextView(getContext());
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTagClickListener != null) {
                    onTagClickListener.onTagClick(tag);
                }
            }
        });
        textView.setText(tag);
        textView.setTextColor(Color.RED);
        textView.setBackgroundColor(Color.BLUE);
        textView.setTextSize(12);
        // TODO: 2019/12/17 最重要的一步 addView
        addView(textView);
    }

    onTagClickListener onTagClickListener;

    public void setOnTagClickListener(FlowLayout.onTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    public interface onTagClickListener {
        void onTagClick(String tag);
    }
}
