package com.bw.combatmoutha.contract;

import com.bw.combatmoutha.model.bean.Bean;
import com.bw.combatmoutha.model.bean.FBean;

public interface IHomeContract {
    interface IView {

        void onFlowSuccess(FBean flowBean);

        void onFlowFailure(Throwable throwable);

        //商品列表成功
        void onHomeSuccess(Bean bean);

        void onHomeFailure(Throwable throwable);


    }

    interface IPresenter {
        //流式布局
        void getFlowData();

        //商品
        void getHomeData(String keyword);
    }

    interface IModel {
        //获取流式布局数据
        void getFlowData(IModelCallback iModelCallback);

        //获取商品列表数据
        void getHomeData(String keyword, IModelCallback iModelCallback);

        interface IModelCallback {

            void onFlowSuccess(FBean flowBean);

            void onFlowFailure(Throwable throwable);

            //商品列表成功
            void onHomeSuccess(Bean bean);

            void onHomeFailure(Throwable throwable);
        }
    }
}
