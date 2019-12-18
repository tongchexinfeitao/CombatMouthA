package com.bw.combatmoutha.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.combatmoutha.R;
import com.bw.combatmoutha.base.BaseFragment;
import com.bw.combatmoutha.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends BaseFragment {


    private TextView textView;

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String key = bundle.getString("key");
            textView.setText(key);
        }
    }

    @Override
    protected void initView(View inflate) {
        textView = inflate.findViewById(R.id.tv);
    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_other;
    }

    public static OtherFragment getInstance(String value) {
        OtherFragment otherFragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", value);
        otherFragment.setArguments(bundle);
        return otherFragment;
    }
}
