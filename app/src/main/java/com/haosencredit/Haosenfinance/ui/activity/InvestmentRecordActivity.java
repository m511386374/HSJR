package com.haosencredit.Haosenfinance.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Nick.Ming on 2017/6/28.
 */

public class InvestmentRecordActivity extends BaseActivity {

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_investment_record;
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
