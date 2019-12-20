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
        //历史记录接口
        void getFlowData();

        //商品搜索接口
        void getHomeData(String keyword);
    }

    interface IModel {
        //历史记录接口
        void getFlowData(IModelCallback iModelCallback);

        //商品搜索接口
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
