package com.bw.combatmoutha.view.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bw.combatmoutha.R;
import com.bw.combatmoutha.base.BaseFragment;
import com.bw.combatmoutha.contract.IHomeContract;
import com.bw.combatmoutha.model.bean.Bean;
import com.bw.combatmoutha.model.bean.FBean;
import com.bw.combatmoutha.presenter.HomePresenter;
import com.bw.combatmoutha.view.activity.SecondActivity;
import com.bw.combatmoutha.view.adapter.MyAdapter;
import com.bw.combatmoutha.view.widget.FlowLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeContract.IView {

    private FlowLayout flowLayout;
    private RecyclerView recyclerView;

    @Override
    protected void initData() {

        //流式布局的数据
        mPresenter.getFlowData();
        //商品数据
        mPresenter.getHomeData("板鞋");
    }

    @Override
    protected void initView(View inflate) {
        recyclerView = inflate.findViewById(R.id.recycler);

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
    public void onFlowSuccess(FBean flowBean) {
        flowLayout.removeAllViews();
        List<String> tags = flowBean.getTags();

        for (int i = 0; i < tags.size(); i++) {
            flowLayout.addTag(tags.get(i));
        }
    }

    @Override
    public void onFlowFailure(Throwable throwable) {
        Toast.makeText(getActivity(), "请求流式布局失败", Toast.LENGTH_SHORT).show();
        Log.e("TAG", "请求流式布局失败 :" + throwable.getMessage());
    }

    @Override
    public void onHomeSuccess(Bean bean) {
        List<Bean.ResultBean> result = bean.getResult();

        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //设置适配器
        MyAdapter myAdapter = new MyAdapter(result);
        myAdapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getActivity(), SecondActivity.class));
            }
        });
        recyclerView.setAdapter(myAdapter);


    }

    @Override
    public void onHomeFailure(Throwable throwable) {
        Toast.makeText(getActivity(), "请求商品失败" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("TAG", "请求商品失败" + throwable.getMessage());

    }
}
