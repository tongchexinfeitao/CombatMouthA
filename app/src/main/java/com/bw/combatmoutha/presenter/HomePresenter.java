package com.bw.combatmoutha.presenter;

import android.view.View;

import com.bw.combatmoutha.base.BasePresenter;
import com.bw.combatmoutha.contract.IHomeContract;
import com.bw.combatmoutha.model.HomeModel;
import com.bw.combatmoutha.model.bean.Bean;
import com.bw.combatmoutha.model.bean.FBean;

public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getFlowData() {
        homeModel.getFlowData(new IHomeContract.IModel.IModelCallback() {
            @Override
            public void onFlowSuccess(FBean flowBean) {
                view.onFlowSuccess(flowBean);
            }

            @Override
            public void onFlowFailure(Throwable throwable) {
                view.onFlowFailure(throwable);
            }

            @Override
            public void onHomeSuccess(Bean bean) {
                view.onHomeSuccess(bean);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onHomeFailure(throwable);
            }
        });
    }

    @Override
    public void getHomeData(String keyword) {
        homeModel.getHomeData(keyword, new IHomeContract.IModel.IModelCallback() {
            @Override
            public void onFlowSuccess(FBean flowBean) {
                view.onFlowSuccess(flowBean);
            }

            @Override
            public void onFlowFailure(Throwable throwable) {
                view.onFlowFailure(throwable);
            }

            @Override
            public void onHomeSuccess(Bean bean) {
                view.onHomeSuccess(bean);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onHomeFailure(throwable);
            }
        });
    }
}
