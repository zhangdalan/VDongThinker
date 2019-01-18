package com.thinker.vdongthinker.tool.http;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("/api/phone/sendVerificationCode")
    Observable<String> getYanCode(@Field("phone") String phone);
    /*检测验证码是否正确*/
    @FormUrlEncoded
    @POST("/api/phone/verityCode")
    Observable<String> getCodeCheck(@Field("phone") String phone ,@Field("code") String code);
    /*验证码登录*/
    @FormUrlEncoded
    @POST("/api/user/vuserInfo/phoneRegistAndLoginUserInfo")
    Observable<String> loginService(@Field("phone") String phone ,@Field("code") String code);

    /*密码登录*/
    @FormUrlEncoded
    @POST("/api/user/vuserInfo/loginByPhoneAndPwd")
    Observable<String> loginPwdService(@Field("phone") String phone ,@Field("pwd") String code);

    /*token登录*/
    @FormUrlEncoded
    @POST("/appi/user/tokenLogin/userLogin")
    Observable<String> loginTokenService(@Field("token") String token);



}