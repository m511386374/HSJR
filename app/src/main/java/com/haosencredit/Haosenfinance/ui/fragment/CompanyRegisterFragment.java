package com.haosencredit.Haosenfinance.ui.fragment;
import android.os.Bundle;

import com.haosencredit.Haosenfinance.R;

import cn.droidlover.xdroidmvp.mvp.XFragment;


/**
 * Created by bruce on 2016/11/1.
 * BaseFragment
 */

public class CompanyRegisterFragment extends XFragment {


    public static CompanyRegisterFragment newInstance() {
        CompanyRegisterFragment fragment = new CompanyRegisterFragment();
        return fragment;
    }
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_companyregister;
    }

    @Override
    public Object newP() {
        return null;
    }


}
