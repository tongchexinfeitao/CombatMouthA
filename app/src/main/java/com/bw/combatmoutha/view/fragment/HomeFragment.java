package com.bw.combatmoutha.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.combatmoutha.R;
import com.bw.combatmoutha.base.BaseFragment;
import com.bw.combatmoutha.contract.IHomeContract;
import com.bw.combatmoutha.model.bean.Bean;
import com.bw.combatmoutha.presenter.HomePresenter;
import com.bw.combatmoutha.view.widget.FlowLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeContract.IView {

    private FlowLayout flowLayout;

    @Override
    protected void initData() {
        // TODO: 2019/12/17 一行代码去联网
        mPresenter.getHomeData("板鞋");
    }

    @Override
    protected void initView(View inflate) {
        flowLayout = inflate.findViewById(R.id.flow);
        flowLayout.setOnTagClickListener(new FlowLayout.onTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                Toast.makeText(getActivity(), tag, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // TODO: 2019/12/17 不要忘了 new HomePresenter
    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onHomeSuccess(Bean bean) {
        flowLayout.removeAllViews();

        Toast.makeText(getActivity(), "成功" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        List<Bean.ResultBean> result = bean.getResult();
        for (int i = 0; i < result.size(); i++) {
            flowLayout.addTag(result.get(i).getPrice()+"");
        }
    }

    @Override
    public void onHomeFailure(Throwable throwable) {
        Toast.makeText(getActivity(), "失败" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("TAG", "失败" + throwable.getMessage());

    }
}
