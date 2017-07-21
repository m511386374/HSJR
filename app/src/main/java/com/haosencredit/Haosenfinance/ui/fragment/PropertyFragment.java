package com.haosencredit.Haosenfinance.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseFragment;
import com.haosencredit.Haosenfinance.ui.activity.BalanceActivity;
import com.haosencredit.Haosenfinance.ui.activity.PropertyDetailActivity;
import com.haosencredit.Haosenfinance.ui.activity.RedPacketActivity;

import butterknife.OnClick;

/**
 * Created by Nick.Ming on 2017/5/11.
 */

public class PropertyFragment extends BaseFragment {


    public static PropertyFragment newInstance() {
        PropertyFragment fragment = new PropertyFragment();
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_property;
    }

    @Override
    public Object newP() {
        return null;
    }

    @OnClick({R.id.balance_amount,R.id.red_packet,R.id.personal_property})
    public void startActivity(View view) {
        switch (view.getId()) {
            case R.id.balance_amount:
                startActivity(BalanceActivity.class);
                break;
            case R.id.red_packet:
                startActivity(RedPacketActivity.class);
                break;
            case R.id.personal_property:
                startActivity(PropertyDetailActivity.class);
                break;
        }
    }
}