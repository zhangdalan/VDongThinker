package com.thinker.vdongthinker.presenter;

import com.thinker.vdongthinker.base.BasePresenter;
import com.thinker.vdongthinker.view.LoginView;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by zjw on 2018/12/6.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
//    /*获取验证码*/
//    public void getYanCode(String str_phone) {
//        LoginBean.CodeBean bean = new LoginBean.CodeBean(str_phone) ;
//        /*发送参数*/
//        Observable<String> dongnao = Observable.just(bean)
//                .flatMap(new Function<LoginBean.CodeBean, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(@NonNull LoginBean.CodeBean codeBean) throws Exception {
//                        return HttpService.getInstance().getYanCode(codeBean.getPhone_num());
//                    }
//                });
//        mView.bindAndupUi(dongnao);
//    }
}
