package com.bw.combatmoutha.contract;

import com.bw.combatmoutha.model.bean.Bean;

public interface IHomeContract {
    interface IView {

        //商品列表成功
        void onHomeSuccess(Bean bean);

        void onHomeFailure(Throwable throwable);


    }

    interface IPresenter {
        void getHomeData(String keyword);
    }

    interface IModel {
        void getHomeData(String keyword, IModelCallback iModelCallback);

        interface IModelCallback {
            void onHomeSuccess(Bean bean);

            void onHomeFailure(Throwable throwable);
        }
    }
}
