package com.haosencredit.Haosenfinance.ui.fragment;
import android.os.Bundle;

import com.haosencredit.Haosenfinance.R;

import cn.droidlover.xdroidmvp.mvp.XFragment;


/**
 * Created by bruce on 2016/11/1.
 * BaseFragment
 */

public class PersonRegisterFragment extends XFragment {


    public static PersonRegisterFragment newInstance() {
        PersonRegisterFragment fragment = new PersonRegisterFragment();
        return fragment;
    }
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personregister;
    }

    @Override
    public Object newP() {
        return null;
    }


}
