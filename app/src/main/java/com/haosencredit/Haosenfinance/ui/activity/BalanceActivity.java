package com.haosencredit.Haosenfinance.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.apkfuns.logutils.LogUtils;
import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseActivity;
import com.haosencredit.Haosenfinance.view.ScoreTrend;
import com.haosencredit.Haosenfinance.view.TimeView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Nick.Ming on 2017/6/14.
 */
public class BalanceActivity extends BaseActivity {

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_balance;
    }

    @Override
    public Object newP() {
        return null;
    }

    @OnClick({R.id.back_image})
    public void back(View view) {
        finish();
    }
}
