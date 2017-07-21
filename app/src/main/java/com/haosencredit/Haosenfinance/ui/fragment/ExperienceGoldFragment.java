package com.haosencredit.Haosenfinance.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseFragment;
import com.haosencredit.Haosenfinance.ui.activity.InvestmentRecordActivity;
import com.haosencredit.Haosenfinance.ui.activity.ReceiveRecordActivity;

import butterknife.OnClick;

/**
 * Created by Nick.Ming on 2017/6/27.
 */

public class ExperienceGoldFragment extends BaseFragment {
    public static ExperienceGoldFragment newInstance() {
        ExperienceGoldFragment fragment = new ExperienceGoldFragment();
        return fragment;
    }
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_experiencegold;
    }

    @Override
    public Object newP() {
        return null;
    }

    @OnClick({R.id.investment_record,R.id.receive_record})
    public void startActivity(View view) {
        switch (view.getId()) {
            case R.id.investment_record:
                startActivity(InvestmentRecordActivity.class);
                break;
            case R.id.receive_record:
                startActivity(ReceiveRecordActivity.class);
                break;
        }
    }

}
