package com.bw.combatmoutha.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), layoutId(), null);
        mPresenter = providePresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        initView(inflate);

        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    protected abstract void initData();

    protected abstract void initView(View inflate);

    protected abstract P providePresenter();

    protected abstract int layoutId();
}
