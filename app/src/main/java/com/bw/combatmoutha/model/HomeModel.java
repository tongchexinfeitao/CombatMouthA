package com.bw.combatmoutha.model;

import com.bw.combatmoutha.contract.IHomeContract;
import com.bw.combatmoutha.model.bean.Bean;
import com.bw.combatmoutha.model.bean.FBean;
import com.bw.combatmoutha.utils.NetUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getFlowData(final IModelCallback iModelCallback) {
        String httpUrl = null;
        try {
            httpUrl = "http://blog.zhaoliang5156.cn/baweiapi/" + URLEncoder.encode("手机", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        NetUtil.getInstance().getJsonGet(httpUrl, new NetUtil.MyCallback() {
            @Override
            public void onGetJsonSuccess(String json) {
                FBean flowBean = new Gson().fromJson(json, FBean.class);
                iModelCallback.onFlowSuccess(flowBean);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallback.onFlowFailure(throwable);
            }
        });
    }

    @Override
    public void getHomeData(String keyword, final IModelCallback iModelCallback) {
        try {
            // TODO: 2019/12/17 如果是中文一定要用 UTF-8 编码
            String encode = URLEncoder.encode(keyword, "UTF-8");
            String httpUrl = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&count=20&keyword=" + encode;
            NetUtil.getInstance().getJsonGet(httpUrl, new NetUtil.MyCallback() {
                @Override
                public void onGetJsonSuccess(String json) {
                    Bean bean = new Gson().fromJson(json, Bean.class);
                    iModelCallback.onHomeSuccess(bean);
                }

                @Override
                public void onError(Throwable throwable) {
                    iModelCallback.onHomeFailure(throwable);
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
