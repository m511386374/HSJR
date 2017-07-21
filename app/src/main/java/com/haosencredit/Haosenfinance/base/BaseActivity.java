package com.haosencredit.Haosenfinance.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.ui.activity.WelcomeActivity;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import qiu.niorgai.StatusBarCompat;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/10/1
 * 描    述：统一管理所有的订阅生命周期
 * 修订历史：
 * ================================================
 */
public abstract class BaseActivity<P extends IPresent> extends XActivity<P> {

    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.finance_background));
    }


}
